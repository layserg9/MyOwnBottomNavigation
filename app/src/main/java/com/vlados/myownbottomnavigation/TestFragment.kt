package com.vlados.myownbottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vlados.myownbottomnavigation.databinding.RandomListBinding
import kotlin.concurrent.timerTask

class VladikTestFragment: Fragment() {
    private var zooAdapter = ZooAdapter(::deleteItem)
    private var _bindingClass: RandomListBinding? = null
    private val bindingClass get() = _bindingClass !!
    private val viewModel: MainViewModel by viewModels()
    private var contentType: Int = 1

    companion object {
        private const val CONTENT_TYPE_KEY = "ContentTypeKey"
        const val ANIMALS_CONTENT = 1
        const val WORKERS_CONTENT = 2
        const val ALL_CONTENT = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingClass = RandomListBinding.inflate(inflater, container, false)
        val view = bindingClass.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindingClass = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentType = arguments?.getInt(CONTENT_TYPE_KEY) ?: 1
        bindingClass.recyclerViewRandom.layoutManager = LinearLayoutManager(requireContext())
        bindingClass.recyclerViewRandom.adapter = zooAdapter
        bindingClass.buttonRandom.text = viewModel.getButtonName(contentType)
        zooAdapter.updateList(viewModel.getItems(contentType))
        bindingClass.buttonRandom.setOnClickListener {
            addItem()
            val scrollPosition = zooAdapter.itemCount.minus(1) ?: 0
            bindingClass.recyclerViewRandom.scrollToPosition(scrollPosition)
        }
    }

    private fun addItem() {
        val newList = viewModel.addItem(contentType)
        zooAdapter.updateList(newList)
    }

    private fun deleteItem(item: ZooItem): Boolean{
        val newList = viewModel.deleteItem(item, contentType)
        zooAdapter.updateList(newList)
        return true
    }
}