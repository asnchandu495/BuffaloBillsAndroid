package com.buffalobills.demo;



import static com.google.android.gms.common.util.CollectionUtils.listOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.buffalobills.demo.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;


public class MainActivity extends AppCompatActivity {
//    EditText editText;
//    TextView textView;
//    private ImageView imageView;
//    private boolean zoomedIn = true;
//    private final long animationDuration = 1000; // Adjust the duration as needed
//    private final long delayBetweenAnimations = 2000;
    LinearLayout bottomLayout, layoutNews, layoutVideos, layoutSchedule, layoutStadium, layoutStore, layoutFanRoom;
    LinearLayout selectedLayout;
    DrawerLayout drawerLayout;
    boolean menuOpen = true;
    ImageView menuIcon, bullLogo;
    NavigationView navigationView;
    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    NewsFragment newsFragment = new NewsFragment();
    VideosFragment videosFragment = new VideosFragment();
    SheduleFragment sheduleFragment = new SheduleFragment();
    StadiumFragment stadiumFragment = new StadiumFragment();
    StoreFragment storeFragment = new StoreFragment();
    FanRoomFragment fanRoomFragment = new FanRoomFragment();

    SettingsFragment settingsFragment = new SettingsFragment();

    public static  String token;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setContentView(R.layout.activity_main);
//        View inflatedView = getLayoutInflater().inflate(R.layout.nav_header, null);
    Bundle bundle = new Bundle();

          bottomLayout = findViewById(R.id.bottomLayout);
          layoutNews = findViewById(R.id.news);
          layoutVideos =  findViewById(R.id.videos);
          layoutSchedule = findViewById(R.id.schedule);
          layoutStadium = findViewById(R.id.stadium);
          layoutStore = findViewById(R.id.store);
          layoutFanRoom = findViewById(R.id.fanRoom);
           menuIcon = findViewById(R.id.menuicon);
//           drawerLayout = findViewById(R.id.drawer);
           navigationView = findViewById(R.id.menuview);
           bullLogo = findViewById(R.id.toolBarlogo);
//           navClosed = (ImageView) inflatedView.findViewById(R.id.navClose);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,newsFragment ).commit();

          layoutNews.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  selectNewsView();
              }
          });

          layoutVideos.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                selectVideosView();

              }
          });
        layoutSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectScheduleView();
            }
        });

        layoutStadium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStadiumView();

            }
        });
        layoutStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selectStoreView();

            }
        });

        layoutFanRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFanRoomView();

            }
        });

        bullLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

//        menuIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                .open();
//            }
//        });
//
//
//        {
//            drawerLayout.open()
//        }
//
//        navigationView.setNavigationItemSelectedListener { menuItem ->
//                // Handle menu item selected
//                menuItem.isChecked = true
//            drawerLayout.close()
//            true
//        }
//        menuIcon.setNavigationOnClickListener {
//            drawerLayout.open()
//        }
//
//        navigationView.setNavigationItemSelectedListener { menuItem ->
//                // Handle menu item selected
//                menuItem.isChecked = true
//            drawerLayout.close()
//            true
//        }



        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(menuOpen){
//                    navigationView.setVisibility(View.VISIBLE);
//                    Log.e("Tag", ""+menuOpen);
//                    menuOpen = !menuOpen;
//                    Log.e("Tag", ""+menuOpen);
//
//                }
                navigationView.setVisibility(View.VISIBLE);
            }
        });
//        navClosed.bringToFront();
//        navClosed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!menuOpen){
//                    navigationView.setVisibility(View.GONE);
//                    Log.e("Tag", ""+menuOpen);
//                    menuOpen = !menuOpen;
//                    Log.e("Tag", ""+menuOpen);
//
//                }
//            }
//        });




//        menuIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.open();
//            }
//        });



//          bottomLayout.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                  if(v.getId() == R.id.news){
//                      layoutNews.setBackgroundResource(R.color.black);
//
//                  } else if (v.getId() == R.id.videos) {
//                      layoutVideos.setBackgroundResource(R.color.white);
//                  }
//              }
//
//
//          });











//        imageView = findViewById(R.id.ImageView);
//        animateImage();

//        editText = findViewById(R.id.etext);
//        textView = findViewById(R.id.textView);

//   bottomNavigationView =findViewById(R.id.bottomNavigationView);
//   getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFragment ).commit();
//   bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//       @Override
//       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//          if(item.getItemId() == R.id.news){
//              getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFragment ).commit();
//          }
//           if(item.getItemId() == R.id.videos){
//               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,videosFragment ).commit();
//           }
//           if(item.getItemId() == R.id.schedule){
//               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,sheduleFragment ).commit();
//           }
//           if(item.getItemId() == R.id.stadium){
//               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,stadiumFragment ).commit();
//           }
//           if(item.getItemId() == R.id.store){
//               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,storeFragment ).commit();
//           }
//
//           return true;
//       }
//   });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.news){
              navigationView.setVisibility(View.GONE);
                    selectNewsView();
          }
           if(item.getItemId() == R.id.videos){
               selectVideosView();
               navigationView.setVisibility(View.GONE);
           }
           if(item.getItemId() == R.id.schedule){
              selectScheduleView();
               navigationView.setVisibility(View.GONE);
           }
           if(item.getItemId() == R.id.stadium){
               navigationView.setVisibility(View.GONE);
               selectStadiumView();
           }
           if(item.getItemId() == R.id.store){
               navigationView.setVisibility(View.GONE);
               selectStoreView();
           }
                if(item.getItemId() == R.id.mafiagpt){
                    navigationView.setVisibility(View.GONE);
                    selectFanRoomView();
                }
                if(item.getItemId() == R.id.fanroom){
                    navigationView.setVisibility(View.GONE);
                    selectFanRoomView();
                }
                if(item.getItemId() == R.id.settings){
                    navigationView.setVisibility(View.GONE);
                    selectSettingsView();
                }

           return true;

            }
        });



        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {


                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                         token = task.getResult();

                        // Log and toast

                        System.out.println(token);
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                        Log.e("Tag", "" + token);


                    }
                });

        bundle.putString("data",token);
        settingsFragment.setArguments(bundle);



    }


             // Adjust the delay between animations as needed



//            private void animateImage() {
//                final Handler handler = new Handler();
//
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        if (zoomedIn) {
//                            zoomOut();
//                            zoomedIn = false;
//                        } else {
//                            zoomIn();
//                            zoomedIn = true;
//                        }
//                        handler.postDelayed(this, delayBetweenAnimations);
//                    }
//                };
//
//                handler.postDelayed(runnable, 0);
//            }
//
//            private void zoomIn() {
//                Animation anim = new ScaleAnimation(1, 1.5f, 1, 1.5f,
//                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                anim.setDuration(animationDuration);
//                anim.setFillAfter(true);
//                imageView.startAnimation(anim);
//            }
//
//            private void zoomOut() {
//                Animation anim = new ScaleAnimation(1.5f, 1, 1.5f, 1,
//                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                anim.setDuration(animationDuration);
//                anim.setFillAfter(true);
//                imageView.startAnimation(anim);
//            }
    void selectNewsView(){
        layoutNews.setBackgroundResource(R.color.whiteTransparentItem);
        layoutFanRoom.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStore.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutSchedule.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStadium.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutVideos.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,newsFragment ).commit();
}

void selectVideosView(){
    layoutNews.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutFanRoom.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutStore.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutSchedule.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutStadium.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutVideos.setBackgroundResource(R.color.whiteTransparentItem);
    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,videosFragment ).commit();
}

    void selectScheduleView(){
        layoutNews.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutFanRoom.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStore.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutSchedule.setBackgroundResource(R.color.whiteTransparentItem);
        layoutStadium.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutVideos.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,sheduleFragment ).commit();
    }

void selectStadiumView(){
    layoutNews.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutFanRoom.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutStore.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutSchedule.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    layoutStadium.setBackgroundResource(R.color.whiteTransparentItem);
    layoutVideos.setBackgroundResource(R.color.whiteTransparentItemDeselct);
    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,stadiumFragment ).commit();
}

    void selectStoreView(){
        layoutNews.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutFanRoom.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStore.setBackgroundResource(R.color.whiteTransparentItem);
        layoutSchedule.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStadium.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutVideos.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,storeFragment ).commit();
    }

    void selectFanRoomView(){
        layoutNews.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutFanRoom.setBackgroundResource(R.color.whiteTransparentItem);
        layoutStore.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutSchedule.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStadium.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutVideos.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fanRoomFragment).commit();
    }

    void selectSettingsView(){
        layoutNews.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutFanRoom.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStore.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutSchedule.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutStadium.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        layoutVideos.setBackgroundResource(R.color.whiteTransparentItemDeselct);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, settingsFragment).commit();

    }


        }





