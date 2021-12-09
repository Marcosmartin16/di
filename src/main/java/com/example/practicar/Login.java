package com.example.practicar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //ocultar appbar
        getSupportActionBar().hide();

        //llamar al logo
        ImageView logo = findViewById(R.id.katana);

       //construir la animacion
        Animation rotate = AnimationUtils.loadAnimation(this,R.anim.roation);
        logo.startAnimation(rotate);

        //Llamar al fondo
        ImageView mfondo = findViewById(R.id.fondo);

        //Cargar fondo con Glide
        //para usar glide meter en el build.gradle
        //implementation 'com.github.bumptech.glide:glide:4.11.0'
        //annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
        Glide.with(this)
                //.load(R.drawable.fondo) por si no hay internet
                .load("https://images.unsplash.com/photo-1637717256696-a0204d03a8fe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80")
                .into(mfondo);
    }

    //funcion que abre el main activity y borra las tareas con las flags se pone en el onclick del boton
    public void openMain(View v){
        Intent intent = new Intent(Login.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //funcion que abre el register activity
    public void openRegister(View v){
        Intent intent = new Intent(Login.this,Register.class);

        startActivity(intent);
    }
}
