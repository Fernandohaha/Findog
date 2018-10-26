package com.example.fernando.myapplication.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.utils.utils.effect.ActivityEffectFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import livroandroid.lib.fragment.BaseFragment;

/**
 * Created by Fernando on 11/11/2017.
 */

public class BaseActivity extends livroandroid.lib.activity.BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected static final int INTERNET = 0;
    protected static final int ACCESS_NETWORK_STATE = 1;
    protected static final int RECORD_AUDIO = 2;
    protected static final int MODIFY_AUDIO_SETTINGS = 3;
    protected static final int READ_PHONE_STATE = 4;
    protected static final int CAMERA = 5;
    protected int notificationsUnseen = 0;
    protected LinearLayout rootView;
    protected ProgressBar progress;

    SharedPreferences prefs;
    public static final String PREFS_LOGIN = "Preferences";

    private final String TAG = this.getClass().getSimpleName();

    private Map<String, AsyncTask> tasks = new HashMap<String, AsyncTask>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_localizacao) {
            show(getActivity(), MapsActivity.class);

        } else if (id == R.id.nav_localizacao) {
            show(getActivity(), MapsActivity.class);
        } else if (id == R.id.nav_cadastrardog) {
            show(getActivity(), CadastrodogActivity.class);
        } else if (id == R.id.nav_Vacina) {
            show(getActivity(), VacinasActivity.class);

        } else if (id == R.id.nav_logout) {

            prefs = getSharedPreferences(LoginActivity.PREFS_LOGIN, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();

            show(getActivity(), LoginActivity.class);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setupNavDrawer(NavigationView.OnNavigationItemSelectedListener listener) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(listener);

            View view = navigationView.getHeaderView(0);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        MenuItem item = menu.getItem(0);
//
//        View notificationView = getLayoutInflater().inflate(R.layout.notification_view, null);
//
//        TextView counter = (TextView) notificationView.findViewById(R.id.counter);
//        counter.setText(String.valueOf(notificationsUnseen));
//
//        item.setActionView(notificationView);
//
//        item.getActionView().setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent callScreen = new Intent(getContext(), NotificationActivity.class);
//                getContext().startActivity(callScreen);
//            }
//        });


        return true;
    }

    public void setupToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void hideToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
    }

    public void startTask(String cod, TaskListener listener) {
        startTask(cod, listener, 0);
    }

    public void startTask(String cod, TaskListener listener, int progressId) {
        Log.d("InterOn", "startTask: " + cod);
        /*View view = getWindow().getDecorView().getRootView();
        if (view == null) {
            throw new RuntimeException("Somente pode iniciar a task se a view do fragment foi criada.\nChame o startTask depois do onCreateView");
        }*/

        Task task = (Task) this.tasks.get(cod);
        if (task == null) {
            // Somente executa se já não está executando
            task = new Task(cod, listener, progressId);
            this.tasks.put(cod, task);
            task.execute();
        }
    }

    private class Task extends AsyncTask<Object, Void, TaskResult> {

        private String cod;
        private TaskListener listener;
        private int progressId;

        private Task(String cod, TaskListener listener, int progressId) {
            this.cod = cod;
            this.listener = listener;
            this.progressId = progressId;
        }

        @Override
        protected void onPreExecute() {
            Log.d("InterOn", "task onPreExecute()");
            showProgress(this, progressId,rootView);
        }

        @Override
        protected TaskResult doInBackground(Object... params) {
            TaskResult r = new TaskResult();
            try {
                r.response = listener.execute();
            } catch (Exception e) {
                Log.e("InterOn", e.getMessage(), e);
                r.exception = e;
            }
            return r;
        }

        protected void onPostExecute(TaskResult result) {
            Log.d("InterOn", "task onPostExecute(): " + result);
            try {
                if (result != null) {
                    if (result.exception != null) {
                        listener.onError(result.exception);
                    } else {
                        listener.updateView(result.response);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                tasks.remove(cod);
                closeProgress(progressId, rootView);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tasks.remove(cod);
            listener.onCancelled(cod);
        }
    }

    private class TaskResult<T> {
        private T response;
        private Exception exception;
    }

    public interface TaskListener<T> {
        // Executa em background e retorna o objeto
        T execute() throws Exception;

        // Atualiza a view na UI Thread
        void updateView(T response) throws IOException, Settings.SettingNotFoundException, InterruptedException;

        // Chamado caso o método execute() lance uma exception
        void onError(Exception exception);

        // Chamado caso a task tenha sido cancelada
        void onCancelled(String cod);
    }

    protected void showProgress(final Task task, int progressId, View v) {
        if (progressId > 0 && v != null) {
            View view = v.findViewById(progressId);
            if (view != null) {
                if (view instanceof SwipeRefreshLayout) {
                    SwipeRefreshLayout srl = (SwipeRefreshLayout) view;
                    if (!srl.isRefreshing()) {
                        srl.setRefreshing(true);
                    }
                } else {
                    view.setVisibility(View.VISIBLE);
                }
                return;
            }
        }
    }

    private void closeProgress(int progressId, View v) {
        if (progressId > 0 && v != null) {
            View view = v.findViewById(progressId);
            if (view != null) {
                if (view instanceof SwipeRefreshLayout) {
                    SwipeRefreshLayout srl = (SwipeRefreshLayout) view;
                    srl.setRefreshing(false);
                } else {
                    view.setVisibility(View.GONE);
                }
                return;
            }
        }

        Log.d("InterOn", "closeProgress()");
//        if (progress != null && progress.isShowing()) {
//            progress.dismiss();
//            progress = null;
//        }
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case INTERNET: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case ACCESS_NETWORK_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MODIFY_AUDIO_SETTINGS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case READ_PHONE_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

//    public void replaceFragment(Fragment fragment, int container) {
//        if (fragment != null) {
//            // replace
//            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//
//            Fragment oldFragment = getContentFragment();
//            if (oldFragment != null && oldFragment instanceof BaseFragment) {
//                BaseFragment base = (BaseFragment) oldFragment;
//                base.release();
//            }
//
//            fragmentManager.beginTransaction().replace(container, fragment, fragment.getTag()).commit();
//        }
//    }
//
//    public Fragment getContentFragment() {
//        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        return (Fragment) fragmentManager.findFragmentByTag("homeFragment");
//    }

    public static void showTop(Activity context, Class<? extends Activity> cls) {
        showTop(context, cls, (Bundle) null);
    }

    public static void showTop(Activity context, Class<? extends Activity> cls, Bundle param) {
        Intent intent = new Intent(context, cls);
        if (param != null) {
            intent.putExtras(param);
        }

        intent.setFlags(67108864);
        if (context != null) {
            context.startActivity(intent);
            ActivityEffectFactory.get().apply(context);
        }

    }

    public static void show(Activity context, Class<? extends Activity> cls) {
        show(context, cls, (Bundle) null);
    }

    public static void show(Activity context, Class<? extends Activity> cls, Bundle params) {
        Intent intent = new Intent(context, cls);
        if (params != null) {
            intent.putExtras(params);
        }

        if (context != null) {
            context.startActivity(intent);
            ActivityEffectFactory.get().apply(context);
        }

    }

    public static void showForResult(Activity context, Class<? extends Activity> cls, Bundle params, int requestCode) {
        Intent intent = new Intent(context, cls);
        if (params != null) {
            intent.putExtras(params);
        }

        if (context != null) {
            context.startActivityForResult(intent, requestCode);
        }

    }

    protected void snack(View view, String msg, String button, final Runnable runnable) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(button, new View.OnClickListener() {
            public void onClick(View v) {
                if (runnable != null) {
                    runnable.run();
                }

            }
        }).show();
    }

    protected void onServiceConnected() {
        // for subclasses
    }

    protected void onServiceDisconnected() {
        // for subclasses
    }


    }




