package ru.surfstudio.android.mvpwidget.sample.ui.screen.main.widget.constraint

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import ru.surfstudio.android.mvp.widget.view.CoreConstraintLayoutView
import ru.surfstudio.android.mvpwidget.sample.R
import javax.inject.Inject

/**
 * Базовый пример виджета на базе {@link ru.surfstudio.android.mvp.widget.view.CoreConstraintLayoutView}
 */
class ConstraintWidgetView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CoreConstraintLayoutView(context, attrs, defStyleAttr) {

    @Inject
    lateinit var presenter: ConstraintViewPresenter

    init {
        View.inflate(context, R.layout.widget_view, this)
        @SuppressLint("SetTextI18n")
        this.findViewById<TextView>(R.id.widget_tv)?.text = "Hello $name"
    }

    override fun getName() = "Constraint widget view"

    override fun createConfigurator() = ConstraintViewConfigurator()

    override fun getPresenters() = arrayOf(presenter)
}