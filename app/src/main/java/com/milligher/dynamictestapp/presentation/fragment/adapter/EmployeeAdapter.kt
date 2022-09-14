package com.milligher.dynamictestapp.presentation.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milligher.dynamictestapp.R
import com.milligher.dynamictestapp.domain.model.employee.Employee

class EmployeeAdapter: RecyclerView.Adapter<EmployeeAdapter.VH>()  {

    private lateinit var context: Context
    private var employeeList: MutableList<Employee> = mutableListOf()

    class VH (itemView: View): RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.fullName)
        val position: TextView = itemView.findViewById(R.id.position)
        val workHoursInMonth: TextView = itemView.findViewById(R.id.workHoursInMonth)
        val workedOutHours: TextView = itemView.findViewById(R.id.workedOutHours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val employeeItem = employeeList[0].data.user

        holder.fullName.text = "fullName: ${employeeItem.fullName}"
        holder.position.text = "position: ${employeeItem.position}"
        holder.workHoursInMonth.text = "workHoursInMonth: ${employeeItem.workHoursInMonth.toString()}"
        holder.workedOutHours.text = "workedOutHours: ${employeeItem.workedOutHours.toString()}"
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun setEmployee(item: Employee){
        employeeList.clear()
        employeeList.add(item)
        notifyDataSetChanged()
    }
}