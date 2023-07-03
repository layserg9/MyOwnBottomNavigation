package com.vlados.myownbottomnavigation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkersListFragment: Fragment(R.layout.workers_list) {
    private var workerAdapter: WorkerAdapter? = null

    private fun getListItems(count: Int): List<WorkerItem> {
        return WorkerFactory().createMultipleWorkers(count)
    }

    private fun getRandomListItem(): WorkerItem {
        return getListItems(20).random()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAdd: Button = view.findViewById(R.id.button_worker)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_worker)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        workerAdapter = WorkerAdapter()
        workerAdapter?.addWorkers(getListItems(4))
        recyclerView.adapter = workerAdapter

        buttonAdd.setOnClickListener {
            workerAdapter?.addWorker(getRandomListItem())
            val scrollPosition = workerAdapter?.itemCount?.minus(1) ?: 0
            if (workerAdapter != null) {
                recyclerView.scrollToPosition(scrollPosition)
            }
        }
    }
}