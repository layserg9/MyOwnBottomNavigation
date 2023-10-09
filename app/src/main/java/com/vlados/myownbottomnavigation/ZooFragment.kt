package com.vlados.myownbottomnavigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vlados.myownbottomnavigation.app.MyApp
import com.vlados.myownbottomnavigation.databinding.RandomListBinding
import javax.inject.Inject

class ZooFragment : Fragment() {
    private var zooAdapter = ZooAdapter(::deleteItem)
    private var bindingClass: RandomListBinding? = null
    private var contentType: ZooListContentType = ZooListContentType.ANIMALS_CONTENT

    @Inject
    lateinit var viewModel: MainViewModel

    companion object {
        private const val CONTENT_TYPE_KEY = "ContentTypeKey"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        bindingClass = RandomListBinding.inflate(inflater, container, false)
        return bindingClass?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingClass = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentType = arguments?.getSerializable("ContentTypeKey") as? ZooListContentType
            ?: ZooListContentType.ANIMALS_CONTENT
        bindingClass?.recyclerViewRandom?.layoutManager = LinearLayoutManager(requireContext())
        bindingClass?.recyclerViewRandom?.adapter = zooAdapter
        bindingClass?.buttonRandom?.text = viewModel.getButtonName(contentType)
        zooAdapter.updateList(viewModel.getItems(contentType))
        bindingClass?.buttonRandom?.setOnClickListener {
            addItem()
            val scrollPosition = zooAdapter.itemCount.minus(1) ?: 0
            bindingClass?.recyclerViewRandom?.scrollToPosition(scrollPosition)
        }
    }

    private fun addItem() {
        val newList = viewModel.addItem(contentType)
        zooAdapter.updateList(newList)
    }

    private fun deleteItem(item: ZooItem): Boolean {
        val newList = viewModel.deleteItem(item, contentType)
        zooAdapter.updateList(newList)
        return true
    }
}