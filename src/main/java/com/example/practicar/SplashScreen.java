package com.example.practicar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash_screen);

            //Llamar al openApp
            openApp(true);

            //Llamar al logo
            ImageView mlogo = findViewById(R.id.katana);

            //construir la animacion creada en el res/anim
            Animation translate = AnimationUtils.loadAnimation(this,R.anim.translation);
            mlogo.startAnimation(translate);

            //Llamar al fondo
            ImageView mFondo = findViewById(R.id.fondo);

            //Cargar fondo con Glide
            //para usar glide meter en el build.gradle
            //implementation 'com.github.bumptech.glide:glide:4.11.0'
            //annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
            Glide.with(this)
                    .load("https://images.unsplash.com/photo-1637717256696-a0204d03a8fe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80")
                    .into(mFondo);
        }

        //añadir flags al splash screen para que no se pueda volver a el ademas
        // de añadirle un tiempo y que salte a otro activity

        public void openApp(boolean locationPermission){

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this,Login.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }
                }, 3000);
            }
}
