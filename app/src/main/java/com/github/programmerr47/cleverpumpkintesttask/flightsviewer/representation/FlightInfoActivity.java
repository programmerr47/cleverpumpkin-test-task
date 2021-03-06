package com.github.programmerr47.cleverpumpkintesttask.flightsviewer.representation;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

import cleverpumpkintesttask.programmerr47.github.com.flightsviewer.R;
import com.github.programmerr47.cleverpumpkintesttask.flightsviewer.clever_api.responseObjects.summary.TripSummary;

/**
 * @author Michael Spitsin
 * @since 2014-08-31
 */
public class FlightInfoActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {
    public static final String TRIP_SUMMARY_INFO = "TRIP_SUMMARY_INFO";
    public static final String CURRENT_OPENED_TRIP = "CURRENT_OPENED_TRIP";

    private List<TripSummary> mSummaries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pager_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSummaries = getSummariesFromParcelableExtra(getIntent().getParcelableArrayExtra(TRIP_SUMMARY_INFO));
        int currentOpenedTripPosition = getIntent().getIntExtra(CURRENT_OPENED_TRIP, 0);

        changeTitleByPosition(currentOpenedTripPosition);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOnPageChangeListener(this);
        pager.setAdapter(new FlightInfoPagerAdapter(getSupportFragmentManager(), mSummaries));
        pager.setCurrentItem(currentOpenedTripPosition);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        changeTitleByPosition(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void changeTitleByPosition(int position) {
        if ((mSummaries.get(position).getFlight() != null) && (mSummaries.get(position).getFlight().getNumber() != null)) {
            setTitle(String.format(getString(R.string.FLIGHT_N), mSummaries.get(position).getFlight().getNumber()));
        } else {
            setTitle(String.format(getString(R.string.FLIGHT_N), "?"));
        }
    }

    private List<TripSummary> getSummariesFromParcelableExtra(Parcelable[] parcelables) {
        List<TripSummary> summaries = new ArrayList<TripSummary>();

        for (Parcelable parcelable : parcelables) {
            summaries.add((TripSummary) parcelable);
        }

        return summaries;
    }

    public static class FlightInfoPagerAdapter extends FragmentStatePagerAdapter {

        private List<TripSummary> mSummaries;

        public FlightInfoPagerAdapter(FragmentManager fm, List<TripSummary> summaries) {
            super(fm);
            mSummaries = summaries;
        }

        @Override
        public Fragment getItem(int i) {
            return FlightInfoFragment.newInstance(mSummaries.get(i));
        }

        @Override
        public int getCount() {
            return mSummaries.size();
        }
    }
}
