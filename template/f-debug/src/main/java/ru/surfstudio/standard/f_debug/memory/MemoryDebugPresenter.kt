package ru.surfstudio.standard.f_debug.memory

import ru.surfstudio.android.core.mvp.presenter.BasePresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.standard.f_debug.server_settings.reboot.RebootDebugActivityRoute
import ru.surfstudio.standard.i_debug.DebugInteractor
import ru.surfstudio.standard.i_debug.storage.MemoryDebugStorage
import javax.inject.Inject

@PerScreen
class MemoryDebugPresenter @Inject constructor(
        basePresenterDependency: BasePresenterDependency,
        private val activityNavigator: ActivityNavigator,
        private val debugInteractor: DebugInteractor
) : BasePresenter<MemoryDebugActivityView>(basePresenterDependency) {

    private val screenModel = MemoryDebugScreenModel(debugInteractor.isLeakCanaryEnabled)

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)
        view.render(screenModel)
    }

    fun setLeakCanaryEnabled(isEnabled: Boolean) {
        debugInteractor.isLeakCanaryEnabled = isEnabled
        activityNavigator.start(RebootDebugActivityRoute())
        activityNavigator.finishAffinity()
    }
}