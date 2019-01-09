package captain.wonjong.mybanner.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import captain.wonjong.mybanner.R;
import captain.wonjong.mybanner.databinding.ActivityMainBinding;
import captain.wonjong.mybanner.util.AutoScrollViewPager;

public class MainActivity extends AppCompatActivity {
    private Context                 mContext;
    private ActivityMainBinding     mBinding;
    private MainViewModel           mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        // DataBinding
        mViewModel = new MainViewModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);

        mViewModel.init(mContext);
        mViewModel.setBanner((AutoScrollViewPager) findViewById(R.id.banner));
    }
}
