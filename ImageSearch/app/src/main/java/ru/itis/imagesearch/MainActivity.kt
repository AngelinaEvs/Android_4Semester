package ru.itis.imagesearch

import android.content.ContentValues
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.itis.imagesearch.presentation.main.MainListFragment
import ru.itis.imagesearch.presentation.results.ResultsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, MainListFragment()).commit()
        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val bundle = Bundle()
                bundle.putString("REQ", query)
                supportFragmentManager.beginTransaction().addToBackStack(ContentValues.TAG)?.replace(R.id.fragment, ResultsFragment.getNewInstance(bundle)).commit()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

}