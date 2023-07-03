package com.vlados.myownbottomnavigation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RandomListFragment : Fragment(R.layout.random_list) {
    private var randomAdapter: RandomAdapter? = null
    private fun getListItems(count: Int): List<RandomItem> {
        return RandomFactory().createMultipleItems(count)
    }

    private fun getRandomListItem(): RandomItem {
        return getListItems(20).random()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonAdd: Button = view.findViewById(R.id.button_random)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_random)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        randomAdapter = RandomAdapter()
        randomAdapter?.addRandoms(getListItems(4))
        recyclerView.adapter = randomAdapter

        buttonAdd.setOnClickListener {
            randomAdapter?.addRandom(getRandomListItem())
            val scrollPosition = randomAdapter?.itemCount?.minus(1) ?: 0
            if (randomAdapter != null) {
                recyclerView.scrollToPosition(scrollPosition)
            }
        }
    }
}