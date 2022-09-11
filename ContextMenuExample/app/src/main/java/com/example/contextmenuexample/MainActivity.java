package com.example.contextmenuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView firstTv, anotherTv;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstTv = findViewById(R.id.first_tv);
        anotherTv = findViewById(R.id.another_tv);
        imageView = findViewById(R.id.image_view);

        registerForContextMenu(firstTv);
        registerForContextMenu(imageView);

        firstTv.setOnLongClickListener(view->{
            Toast.makeText(this, "Long click", Toast.LENGTH_SHORT).show();
            view.startActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    getMenuInflater().inflate(R.menu.textview_menu,menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.item_one ){
                        Toast.makeText(MainActivity.this, "Text view item one selected", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else if (menuItem.getItemId() == R.id.item_two){
                        Toast.makeText(MainActivity.this, "Text view item two selected", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }
                @Override
                public void onDestroyActionMode(ActionMode actionMode) {

                }
            });
            return true;
        });

        anotherTv.setOnClickListener(view->{
            PopupMenu popupMenu = new PopupMenu(this,view);
            getMenuInflater().inflate(R.menu.textview_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.item_one ){
                    Toast.makeText(this, "Text view item one selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (menuItem.getItemId() == R.id.item_two){
                    Toast.makeText(this, "Text view item two selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
            popupMenu.show();

        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.image_view){
            getMenuInflater().inflate(R.menu.image_view_menu,menu);
        }

        else if (v.getId() == R.id.first_tv){
            getMenuInflater().inflate(R.menu.textview_menu,menu);
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() ==  R.id.action_save) {
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.item_one ){
            Toast.makeText(this, "Text view item one selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.item_two){
            Toast.makeText(this, "Text view item two selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}