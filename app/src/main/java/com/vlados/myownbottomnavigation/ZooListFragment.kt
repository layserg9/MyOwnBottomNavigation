package com.vlados.myownbottomnavigation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ZooListFragment : Fragment(R.layout.random_list) {
    private var zooAdapter: ZooAdapter? = null
    private fun getListItems(count: Int): List<ZooItem> {
        return RandomFactory().createMultipleItems(count)
    }

    private fun getRandomListItem(): ZooItem {
        return getListItems(20).random()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonAdd: Button = view.findViewById(R.id.button_random)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_random)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        zooAdapter = ZooAdapter()
        zooAdapter?.addZooListItems(getListItems(4))
        recyclerView.adapter = zooAdapter

        buttonAdd.setOnClickListener {
            zooAdapter?.addZooListItem(getRandomListItem())
            val scrollPosition = zooAdapter?.itemCount?.minus(1) ?: 0
            if (zooAdapter != null) {
                recyclerView.scrollToPosition(scrollPosition)
            }
        }
    }
}