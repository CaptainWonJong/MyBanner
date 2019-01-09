package captain.wonjong.mybanner.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import captain.wonjong.mybanner.main.adapter.BannerAdapter;
import captain.wonjong.mybanner.main.model.BannerBackModel;
import captain.wonjong.mybanner.util.AutoScrollViewPager;
import captain.wonjong.mybanner.util.Buffer;
import captain.wonjong.mybanner.util.JsonToMap;
import captain.wonjong.mybanner.util.MapCtl;
import captain.wonjong.mybanner.util.RPKey;

public class MainViewModel extends ViewModel {
    public MutableLiveData<ArrayList<HashMap<String, Object>>> bannerList = new MutableLiveData<>();
    public MutableLiveData<Integer> bannerInterval = new MutableLiveData<>();
    public MutableLiveData<Boolean> isAutoScroll = new MutableLiveData<>();

    private AutoScrollViewPager mBanner;

    public BannerBackModel bgModel;


    public BannerAdapter bannerAdapter;

    public void init(Context context) {
        testSetData();
        bgModel = new BannerBackModel();

        bannerAdapter = new BannerAdapter(context, bannerList, bgModel);
        bannerAdapter.setOnItemClickListener(onBannerItemClickListener);
        isAutoScroll.setValue(true);
        bannerInterval.setValue(3000);
    }

    public void setBanner(AutoScrollViewPager banner) {
        mBanner = banner;
    }

    private void testSetData() {
        String response = Buffer.dummyData;
        try {
            HashMap<String, Object> mapResponse = JsonToMap.getJsonToMapBase(response);
            ArrayList<HashMap<String, Object>> campaignPromotionList = MapCtl.newInstance(mapResponse).getMtoArrData(RPKey.PROMOTION_BANNER_LIST).getArrayMap();

            bannerList.setValue(campaignPromotionList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BannerAdapter.OnItemClickListener onBannerItemClickListener = new BannerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Log.e("testest0", position + " ----------------------------- ");
        }
    };

    public AutoScrollViewPager.OnPageChangeListener onPageChangeListener = new AutoScrollViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            Log.e("testest1", i + " ----------------------------- \n"
                    + v + " ----------------------------- \n"
                    + i1 + " ----------------------------- \n"
            );
        }

        @Override
        public void onPageSelected(int i) {
            Log.e("testest2", i + " ----------------------------- \n");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                // 페이지 커스텀 (순환 구조 구현)
                // page 수 + 2개만큼 더 view 생성한 뒤,
                // 왼쪽 맨 마지막에는 가장 오른쪽에있는 view를, 오른쪽 맨 마지막에는 가장 왼쪽에있는 view 생성
                int pageCount = bannerAdapter.getCount();
                if (pageCount > 1) {
                    // left edge
                    if (mBanner.getCurrentItem() == 0) {
                        mBanner.setCurrentItem(pageCount - 2, false);
                    }
                    // right edge
                    else if (mBanner.getCurrentItem() == pageCount - 1) {
                        mBanner.setCurrentItem(1, false);
                    }
                }
            }
        }
    };
}
