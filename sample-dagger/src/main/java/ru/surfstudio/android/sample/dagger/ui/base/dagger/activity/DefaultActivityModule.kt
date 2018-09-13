package ru.surfstudio.android.sample.dagger.ui.base.dagger.activity

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.ui.bus.RxBus
import ru.surfstudio.android.core.ui.event.ScreenEventDelegateManager
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigatorForActivity
import ru.surfstudio.android.core.ui.navigation.fragment.FragmentNavigator
import ru.surfstudio.android.core.ui.permission.PermissionManager
import ru.surfstudio.android.core.ui.permission.PermissionManagerForActivity
import ru.surfstudio.android.core.ui.provider.ActivityProvider
import ru.surfstudio.android.core.ui.scope.ActivityPersistentScope
import ru.surfstudio.android.core.ui.scope.PersistentScope
import ru.surfstudio.android.core.ui.state.ActivityScreenState
import ru.surfstudio.android.dagger.scope.PerActivity
import ru.surfstudio.android.message.DefaultMessageController
import ru.surfstudio.android.message.MessageController
import ru.surfstudio.android.picturechooser.PicturePermissionChecker
import ru.surfstudio.android.picturechooser.PictureProvider

/**
 * Модуль для dagger Activity Component
 * поставляет ряд сущностей, например навигаторы, причем они находятся в @PerActivity scope
 * и не пробрасываются в дочерние scope, эти обьекты могут быть использованы без презентера,
 * например открытие необходимого фрагмента с помощью FragmentNavigator из активити контейнера.
 * Эти обьекты могут также использоваться внутри дополнительных обектов со специфической логикой,
 * принадлежащих скоупу @PerScreen
 */

@Module
class DefaultActivityModule(private val persistentScope: ActivityPersistentScope) {

    @Provides
    @PerActivity
    internal fun provideActivityPersistentScope(): ActivityPersistentScope {
        return persistentScope
    }

    @Provides
    @PerActivity
    internal fun providePersistentScope(persistentScope: ActivityPersistentScope): PersistentScope {
        return persistentScope
    }

    @Provides
    @PerActivity
    internal fun provideActivityScreenState(): ActivityScreenState {
        return persistentScope.screenState
    }

    @Provides
    @PerActivity
    internal fun provideActivityProvider(): ActivityProvider {
        return ActivityProvider(persistentScope.screenState)
    }

    @Provides
    @PerActivity
    internal fun provideActivityNavigator(activityProvider: ActivityProvider,
                                          eventDelegateManager: ScreenEventDelegateManager): ActivityNavigator {
        return ActivityNavigatorForActivity(activityProvider, eventDelegateManager)
    }

    @Provides
    @PerActivity
    internal fun provideEventDelegateManager(): ScreenEventDelegateManager {
        return persistentScope.screenEventDelegateManager
    }

    @Provides
    @PerActivity
    internal fun providePermissionManager(eventDelegateManager: ScreenEventDelegateManager,
                                          activityProvider: ActivityProvider): PermissionManager {
        return PermissionManagerForActivity(eventDelegateManager, activityProvider)
    }

    @Provides
    @PerActivity
    internal fun provideMessageController(activityProvider: ActivityProvider): MessageController {
        return DefaultMessageController(activityProvider)
    }

    @Provides
    @PerActivity
    internal fun provideFragmentNavigator(activityProvider: ActivityProvider): FragmentNavigator {
        return FragmentNavigator(activityProvider)
    }

    @Provides
    @PerActivity
    internal fun provideRxBus(): RxBus {
        return RxBus()
    }

    @Provides
    @PerActivity
    internal fun providePicturePermissionChecker(permissionManager: PermissionManager): PicturePermissionChecker {
        return PicturePermissionChecker(permissionManager)
    }

    @Provides
    @PerActivity
    internal fun providePictureProvider(context: Context,
                                        activityNavigator: ActivityNavigator,
                                        activityProvider: ActivityProvider,
                                        ppChecker: PicturePermissionChecker): PictureProvider {
        return PictureProvider(context, activityNavigator, activityProvider, ppChecker)
    }
}
