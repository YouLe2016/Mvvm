package com.example.mvvm.view.project;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mvvm.R;
import com.example.mvvm.model.project.Projects;
import com.example.mvvm.model.ui.ListStatus;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.widget.state.NetworkStateView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

public class ProjectActivity extends AppCompatActivity implements NetworkStateView.OnRetryClickListener {

    private RecyclerView recyclerView;
    private ProjectAdapter adapter;

    private ProjectViewModel projectsViewModel;

    private ListStatus status = ListStatus.Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        initView();
        initData();
    }

    private void initData() {
        projectsViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        projectsViewModel.getProjects().observe(this, this::update);
        projectsViewModel.load(page);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter();
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(refreshListener);

        networkSateView = findViewById(R.id.network_sate_view);
        networkSateView.setOnRetryClickListener(this);
    }

    private NetworkStateView networkSateView;

    @Override
    public void onReload() {
        status = ListStatus.Content;
        projectsViewModel.load(page = 1);
    }

    private TwinklingRefreshLayout refreshLayout;

    private int page = 1;

    private RefreshListenerAdapter refreshListener = new RefreshListenerAdapter() {
        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            status = ListStatus.Refreshing;
            projectsViewModel.load(page = 1);
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            status = ListStatus.LoadingMore;
            projectsViewModel.load(++page);
        }
    };



    private void update(StateModel<Projects> projects) {
        switch (projects.getStatus()) {
            case Content:
                Projects data = projects.getData();
                switch (status) {
                    case Refreshing:
                        adapter.setData(data.getItems());
                        refreshLayout.finishRefreshing();
                        break;
                    case LoadingMore:
                        adapter.addData(data.getItems());
                        refreshLayout.finishLoadmore();
                        break;
                    default:
                        adapter.setData(data.getItems());
                        networkSateView.showSuccess();
                        break;
                }
                break;
            case Empty: {
                switch (status) {
                    case Refreshing:
                        refreshLayout.finishRefreshing();
                        networkSateView.showEmpty();
                        break;
                    case LoadingMore:
                        refreshLayout.finishLoadmore();
                        break;
                    default:
                        networkSateView.showEmpty();
                        break;
                }
                break;
            }
            case Error: {
                String message = projects.getError().getMessage();
                switch (status) {
                    case Refreshing:
                        refreshLayout.finishRefreshing();
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                        break;
                    case LoadingMore:
                        refreshLayout.finishLoadmore();
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        networkSateView.showError();
                        break;
                }
                break;
            }
            case Loading: {
                switch (status) {
                    case Refreshing:
                        break;
                    case LoadingMore:
                        break;
                    default:
                        networkSateView.showLoading();
                        break;
                }
                break;
            }
            case NoNet: {
                switch (status) {
                    case Refreshing:
                        refreshLayout.finishRefreshing();
                        break;
                    case LoadingMore:
                        refreshLayout.finishLoadmore();
                        break;
                    default:
                        networkSateView.showNoNetworkRetry();
                        break;
                }
                break;
            }
            default:
                break;
        }
    }

}
