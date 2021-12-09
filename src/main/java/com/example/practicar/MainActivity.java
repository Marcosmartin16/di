package com.example.practicar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //inicializar visorweb y swipe
    private WebView mivisorweb;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Llamar al mycontext para que funcione el context menu
        WebView mycontext = (WebView) findViewById(R.id.myWeb);
        registerForContextMenu(mycontext);

        //Llamar al swipe
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);

        //Llamar al WebView
        mivisorweb = (WebView) findViewById(R.id.myWeb);
        //url que carga el webview
        mivisorweb.loadUrl("https://www.thisfuckeduphomerdoesnotexist.com/");
        //poner para hacer zoom en el webview
        mivisorweb.getSettings().setBuiltInZoomControls(true);
    }

    //Listener para el swipe y creacion del toast
    protected SwipeRefreshLayout.OnRefreshListener
        mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Toast toast1 = Toast.makeText(MainActivity.this,"TOAST",Toast.LENGTH_SHORT);
            toast1.show();

            /* Toast con imagen quitar lo anterior y poner lo siguiente
            LayoutInflater myInflater = getLayoutInflater();
            View myLayout = myInflater.inflate(R.layout.toast_custom,null);
            Toast mytoast = new Toast(getApplicationContext());
            mytoast.setGravity(Gravity.BOTTOM,0,0);
            mytoast.setDuration(Toast.LENGTH_SHORT);
            mytoast.setView(myLayout);
            mytoast.show();*/

            mivisorweb.reload();

            swipeLayout.setRefreshing(false);
        }
    };

    //crear el context menu ya creado en res/menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
    }

    //damos uso a los items del menu_context
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:

                //snackbar myMainConstraint se le pone como id al constraint del main
                final ConstraintLayout mLayout = findViewById(R.id.myMainConstraint);

                Snackbar snackbar = Snackbar
                        .make(mLayout,"Item copied", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar snackbar1 = Snackbar.make(mLayout, "Item not copied", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                snackbar.show();

                return true;

            case R.id.item2:

                //toast
                Toast toast2 = Toast.makeText(this,"Item download", Toast.LENGTH_SHORT);
                toast2.show();

                return true;

            default:
        }
        return super.onContextItemSelected(item);
    }

    //Creamos el options menu ya creado en el res/menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar,menu);
        return true;
    }

    //damos uso a los items del options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item1){
            Toast toast = Toast.makeText(this,"fav",Toast.LENGTH_SHORT);
            toast.show();
        }
        if(id == R.id.item2){
            Toast toast = Toast.makeText(this,"search",Toast.LENGTH_SHORT);
            toast.show();
        }
        if(id == R.id.item3){
            //llamamos a la funcion showAlertDialogButtonClicked para que nos aparezca el alertdialog
            showAlertDialogButtonClicked(MainActivity.this);
        }
        if(id == R.id.item4){
            //abre el register activity
            Intent intent = new Intent(MainActivity.this,Register.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    //Creamos el AlertDialog

    public void showAlertDialogButtonClicked(MainActivity view){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

        builder.setTitle("Wait a minute!");
        builder.setMessage("Who are you?");
        builder.setIcon(R.drawable.ic_katana);

        //si queremos que solo salga una imagen en lugar de lo anterior creamos un layout
        // en res/layout con el nombre toast_custom con la imagen en un LineaeLayout y dentro el ImageView
        // y la siguiente linea y comentamos lo anterior
        //builder.setView(getLayoutInflater().inflate(R.layou.toast_custom,null));

        //boton de si
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //boton de no
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //boton neutral
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}