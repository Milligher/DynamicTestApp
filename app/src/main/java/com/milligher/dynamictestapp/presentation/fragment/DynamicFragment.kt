package com.milligher.dynamictestapp.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.milligher.dynamictestapp.App
import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.domain.model.configuration.Text
import com.milligher.dynamictestapp.presentation.fragment.adapter.EmployeeAdapter
import javax.inject.Inject

class DynamicFragment : Fragment() {

    lateinit var configuration: Configuration

    @Inject
    lateinit var viewModelFactory: DynamicFragmentViewModelFactory
    private lateinit var viewModel: DynamicFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (context?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DynamicFragmentViewModel::class.java)

        val mainLayout = LinearLayout(context)
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mainLayout.addView(linearLayout)

        val recyclerView = RecyclerView(context!!)
        recyclerView.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.MATCH_PARENT
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = EmployeeAdapter()
        recyclerView.adapter = adapter
        mainLayout.addView(recyclerView)

        val layoutConfiguration = configuration.activities[0].layout

        val header = TextView(context)
        header.text = layoutConfiguration.header.toString()
        header.textSize = 26F
        header.setBackgroundColor(Color.BLACK)
        header.setTextColor(Color.WHITE)
        header.gravity = Gravity.CENTER_HORIZONTAL
        header.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearLayout.addView(header)

        layoutConfiguration.form.text.forEach {
            when (it.type) {
                "auto-complete-text-view" -> {
                    linearLayout.addView(createAutoCompleteTextView(configuration = it))
                }
                //я не знаю какие еще типы может вернуть API, если есть другие то просто добавить сюда
                else -> {
                    linearLayout.addView(createEditText(configuration = it))
                }
            }
        }

        layoutConfiguration.form.buttons.forEach {
            linearLayout.addView(createButton(configuration = it, layout = linearLayout))
        }

        viewModel.getEmployeeLiveDataItem().observe(viewLifecycleOwner) {
            adapter.setEmployee(it)
        }

        return mainLayout
    }

    private fun createButton(
        configuration: com.milligher.dynamictestapp.domain.model.configuration.Button,
        layout: LinearLayout
    ): Button {
        val button = Button(context)
        button.text = configuration.caption
        button.setOnClickListener {

            val params: MutableMap<String, String> = mutableMapOf()
            layout.forEach {
                if (it.tag != null) {
                    val paramText = (it as EditText).text.toString().trim()
                    params.put(it.tag.toString(), paramText)
                }
            }

            var employee = configuration.formAction
            if (employee == "/") employee = ""
            else employee.substring(1)

            Log.d("MYTAG", params.toString())
            Log.d("MYTAG", employee)

            viewModel.getEmployee(employee = employee, params = params)
        }
        return button
    }

    private fun createEditText(configuration: Text): EditText {
        val view = EditText(context)
        view.hint = configuration.caption
        view.tag = configuration.attribute
        return view
    }

    private fun createAutoCompleteTextView(configuration: Text): AutoCompleteTextView {
        val view = AutoCompleteTextView(context)
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            context!!,
            android.R.layout.simple_dropdown_item_1line,
            configuration.suggestions
        )
        view.setAdapter(adapter)
        view.hint = configuration.caption
        view.threshold = 0
        view.tag = configuration.attribute
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(conf: Configuration) =
            DynamicFragment().apply {
                arguments = Bundle().apply {
                   configuration = conf
                }
            }
    }
}