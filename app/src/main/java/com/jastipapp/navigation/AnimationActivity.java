package com.jastipapp.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.view.ViewGroup;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        Scene aScene;
        Scene anotherScene;

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);

        // Create the scenes
        aScene = Scene.getSceneForLayout(sceneRoot, R.layout.a_scene, this);
        anotherScene =
                Scene.getSceneForLayout(sceneRoot, R.layout.another_scene, this);

    }
}
