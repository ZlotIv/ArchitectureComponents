package main.di.components

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import main.ui.ViewModelFactory
import main.ui.ViewModelKey
import main.ui.list.ListFragment
import main.ui.list.ListFragmentViewModel
import main.ui.main.MainActivity
import main.ui.main.MainActivityViewModel
import javax.inject.Scope

@Subcomponent(modules = [ContactModule::class])
@ContactScope
interface ContactComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ContactComponent
    }

    fun inject(listFragment: ListFragment)
    fun inject(mainActivity: MainActivity)


}

@Module
abstract class ContactModule {
    @ContactScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContactScope
    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    abstract fun listFragmentViewModel(viewModel: ListFragmentViewModel): ViewModel

    @ContactScope
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME) annotation class ContactScope