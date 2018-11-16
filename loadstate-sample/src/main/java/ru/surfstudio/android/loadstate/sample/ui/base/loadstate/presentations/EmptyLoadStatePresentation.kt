package ru.surfstudio.android.loadstate.sample.ui.base.loadstate.presentations

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.surfstudio.android.core.mvp.loadstate.renderer.LoadStatePresentation
import ru.surfstudio.android.core.mvp.model.state.LoadStateInterface
import ru.surfstudio.android.loadstate.sample.R
import ru.surfstudio.android.loadstate.sample.ui.base.loadstate.renderer.PlaceHolderViewContainer
import ru.surfstudio.android.loadstate.sample.ui.base.loadstate.states.EmptyLoadState

class EmptyLoadStatePresentation(private val placeHolder: PlaceHolderViewContainer) :
        LoadStatePresentation<EmptyLoadState> {

    @DrawableRes
    private var imageRes: Int = R.drawable.ic_empty_state

    @StringRes
    private var titleRes: Int = R.string.empty_state_title_txt

    @StringRes
    private var subtitleRes: Int = R.string.empty_state_subtitle_txt

    @StringRes
    private var btnRes: Int = R.string.empty_state_action_btn

    private var onBtnClickedListener: () -> Unit = {}

    private lateinit var container: LinearLayout
    private lateinit var imageIv: ImageView
    private lateinit var titleTv: TextView
    private lateinit var subtitleTv: TextView
    private lateinit var actionBtn: Button


    private val view: View by lazy {
        with(LayoutInflater.from(placeHolder.context).inflate(
                R.layout.no_data_state_presentation,
                placeHolder,
                false)) {
            findViews(this)
            return@lazy this
        }
    }

    override fun showPresentation(loadStateFrom: LoadStateInterface, loadStateTo: EmptyLoadState) {
        initViews(view)
        placeHolder.changeViewTo(view)
        placeHolder.changeVisibility(View.VISIBLE)
    }

    override fun hidePresentation(loadStateFrom: EmptyLoadState, loadStateTo: LoadStateInterface) {
    }

    fun configState(@DrawableRes imageRes: Int? = null,
                    @StringRes titleRes: Int? = null,
                    @StringRes subtitleRes: Int? = null,
                    @StringRes btnRes: Int? = null,
                    onBtnClickedListener: (() -> Unit)? = null) {
        imageRes?.let { this.imageRes = it }
        titleRes?.let { this.titleRes = it }
        subtitleRes?.let { this.subtitleRes = it }
        btnRes?.let { this.btnRes = it }
        onBtnClickedListener?.let { this.onBtnClickedListener = it }
    }

    private fun findViews(view: View) {
        container = view.findViewById(R.id.placeholder_view_no_data_strategy_container)
        imageIv = view.findViewById(R.id.placeholder_image_iv)
        titleTv = view.findViewById(R.id.placeholder_title_tv)
        subtitleTv = view.findViewById(R.id.placeholder_subtitle_tv)
        actionBtn = view.findViewById(R.id.placeholder_first_btn)
    }

    private fun initViews(view: View) {

        imageIv.setImageResource(imageRes)
        titleTv.text = view.context.getString(titleRes)
        subtitleTv.text = view.context.getString(subtitleRes)
        actionBtn.text = view.context.getString(btnRes)

        actionBtn.setOnClickListener { onBtnClickedListener.invoke() }
    }
}