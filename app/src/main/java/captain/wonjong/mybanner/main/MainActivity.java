package captain.wonjong.mybanner.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
        mViewModel.setBanner(mBinding.vpBanner);

        ImageView iv = (ImageView) findViewById(R.id.iv_test);

        // create an animation for your view and set the property you want to animate
        SpringAnimation animation = new SpringAnimation(iv, SpringAnimation.TRANSLATION_X);
        // create a spring with desired parameters
        SpringForce spring = new SpringForce();
        // can also be passed directly in the constructor
        spring.setFinalPosition(100f);
        // optional, default is STIFFNESS_MEDIUM
        spring.setStiffness(SpringForce.STIFFNESS_LOW);
        // optional, default is DAMPING_RATIO_MEDIUM_BOUNCY
        spring.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        // set your animation's spring
        animation.setSpring(spring);
        // animate!
        animation.start();

    }
}
