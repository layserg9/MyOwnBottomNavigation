package com.vlados.myownbottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnimalListFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
            return inflater.inflate(R.layout.zoo_list, container, false)
    }

    private var zooAdapter: ZooAdapter? = null

    private fun getListItems(count: Int): List<AnimalItem> {
        return AnimalFactory().createMultipleAnimals(count)
    }

    private fun getRandomListItem(): AnimalItem {
        return getListItems(20).random()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAdd: Button = view.findViewById(R.id.button_zoo)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_zoo)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        zooAdapter = ZooAdapter()
        zooAdapter?.addZooListItems(getListItems(5))
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

