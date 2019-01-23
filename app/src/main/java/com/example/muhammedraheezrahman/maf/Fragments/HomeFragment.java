package com.example.muhammedraheezrahman.maf.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.muhammedraheezrahman.maf.Adapter.CustomPagerAdapter;
import com.example.muhammedraheezrahman.maf.Adapter.RecyclerCartAdapter;
import com.example.muhammedraheezrahman.maf.Adapter.RecyclerCartAdapter.CartItemClickListener;
import com.example.muhammedraheezrahman.maf.Adapter.RecyclerCategoryAdapter;
import com.example.muhammedraheezrahman.maf.Adapter.RecyclerProductAdapter;
import com.example.muhammedraheezrahman.maf.Database.DatabaseHelper;
import com.example.muhammedraheezrahman.maf.Model.Product;
import com.example.muhammedraheezrahman.maf.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements RecyclerProductAdapter.ItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static  ViewPager viewPager;
    private ImageView imageView;
    private Timer timer;
    private final long DELAY_TIME = 500;
    private final long PERIOD_TIME = 2500;
    private  int CURRENT_PAGE = 0;
    private  int NO_OF_PAGE = 0;
    private RecyclerCategoryAdapter adapter_categories;
    private RecyclerCategoryAdapter adapter_electronics;
    private RecyclerProductAdapter adapterHomeProducts;
    private RecyclerView recyclerView_categories;
    private RecyclerView recyclerView_electronics;
    private RecyclerView recyclerView_smartphones;
    private LinearLayoutManager linearLayoutManager_categories;
    private LinearLayoutManager linearLayoutManager_electronics;
    private LinearLayoutManager linearLayoutManager_smartPhones;
    private DatabaseHelper databaseHelper;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view =  inflater.inflate(R.layout.fragment_home, container, false);
        databaseHelper = new DatabaseHelper(getActivity());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        String urlImageSlider[] = new String[]{"https://image.shutterstock.com/image-vector/sale-banner-template-design-big-450w-1005958072.jpg"
                                    ,"https://image.shutterstock.com/z/stock-vector-vector-sale-faceted-d-banner-poster-colorful-illustration-572608936.jpg"
                                    ,"https://image.shutterstock.com/image-vector/bright-sale-banner-design-vector-450w-730408768.jpg"
                                    ,"https://image.shutterstock.com/image-photo/sale-concept-collection-female-shoes-450w-455544916.jpg"
                                    ,"https://image.shutterstock.com/image-vector/sale-poster-shopping-bag-450w-606454742.jpg"};

        recyclerView_categories = (RecyclerView) view.findViewById(R.id.rv_categories);
        recyclerView_electronics = (RecyclerView) view.findViewById(R.id.rv_electronics);
        recyclerView_smartphones= (RecyclerView) view.findViewById(R.id.rv_smartPhone);
        linearLayoutManager_categories = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager_electronics = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager_smartPhones = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);


//        recyclerView_electronics.setLayoutManager(linearLayoutManager);
        recyclerView_categories.setLayoutManager(linearLayoutManager_categories);
        recyclerView_electronics.setLayoutManager(linearLayoutManager_electronics);
        recyclerView_smartphones.setLayoutManager(linearLayoutManager_smartPhones);

        NO_OF_PAGE = urlImageSlider.length;

        PagerAdapter pagerAdapter = new CustomPagerAdapter(urlImageSlider,getActivity());
        viewPager.setAdapter(pagerAdapter);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (CURRENT_PAGE == NO_OF_PAGE -1){
                   CURRENT_PAGE = 0;

                }
                viewPager.setCurrentItem(CURRENT_PAGE++,true);

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);

        //categories recycler View
        String CategoriesImages[] = new String[]{"https://image.shutterstock.com/z/stock-photo-close-up-of-salmon-steak-with-fresh-ingredients-for-tasty-cooking-on-rustic-wooden-background-380419306.jpg"
                                                 ,"https://image.shutterstock.com/z/stock-photo-shopping-groceries-bags-759471532.jpg"
                                                 ,"https://image.shutterstock.com/z/stock-photo-flat-lay-photo-baby-stuff-sponge-yellow-liquid-soap-package-blue-shampoo-bottle-pink-shower-gel-1074999122.jpg"
                                                    };
        String categoriesTitles[] = new String[]{"Fresh Food","Groceries","Baby World"};
        adapter_categories = new RecyclerCategoryAdapter(getActivity().getApplicationContext(),CategoriesImages,categoriesTitles);
        recyclerView_categories.setAdapter(adapter_categories);

        //electronics recycler View
        String electronicsImages[] = new String[]{"https://image.shutterstock.com/image-photo/electronic-circuit-board-close-up-450w-1242399118.jpg"
                                                    ,"http://keralaonlinechannel.com/upload/images/5bt4k4.jpg"
                                                    ,"https://qalebfa.com/wp-content/uploads/wooden-royal-sofa-couch-3-seater-teak-wood-white-home-furniture.jpg"};
        String electonicsTitles[] = new String[]{"Electonics","Home Appliances","Home furniture"};
        adapter_electronics = new RecyclerCategoryAdapter(getActivity().getApplicationContext(),electronicsImages,electonicsTitles);
        recyclerView_electronics.setAdapter(adapter_electronics);


        //Mobiles recycler View

        String smartphoneImageURL[] = new String[]{"https://cdn.vox-cdn.com/thumbor/3kLqQ-F8ldNtSKXtSJKXabx0RGg=/0x0:705x470/1200x800/filters:focal(297x179:409x291)/cdn.vox-cdn.com/uploads/chorus_image/image/61477359/galaxy_a7_main_1.0.jpg"
                                                    ,"https://images.sellbrite.com/production/14465/SAM-G892U_64-GRY-TMOB-9/668e6064-1586-552c-b321-a00f1f1bac1f.jpg"
                                                    ,"http://www.three.co.uk/static/images/device_pages/MobileVersion/Huawei/Mate_20_Pro/Black/desktop/1.jpg"
                                                    ,"https://i.gadgets360cdn.com/large/asus_zenfone_max_pro_m1_1525410691515.jpg"
                                                    ,"https://www.jarir.com/media/catalog/product/cache/1/image/400x400/9df78eab33525d08d6e5fb8d27136e95/5/1/513984.jpg"
                                                    ,"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone8/plus/iphone8-plus-silver-select-2018?wid=513&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1522347733364"
                                                    ,"https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/BlackBerry-Priv.jpg/1200px-BlackBerry-Priv.jpg"
                                                    ,"https://photos.dialcom.lk/big/items/-passport-mobile-phones-price-in-sri-lanka_374_jpg"};
        List<Product> productList = new ArrayList<>();
        productList = databaseHelper.getProducts();
        adapterHomeProducts = new RecyclerProductAdapter(productList,getActivity(),this);
        recyclerView_smartphones.setAdapter(adapterHomeProducts);



        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.changeBottomNavSelection(R.id.navigation_home);
        }

    }


    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();

    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void addToCart(int id) {
        databaseHelper.addToCart(id);
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
        void changeBottomNavSelection(int menuItem);

    }


}
