package ru.surfstudio.android.recycler.extension.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.recycler.extension.addStickyFooter
import ru.surfstudio.android.recycler.extension.addStickyHeader
import ru.surfstudio.android.recycler.extension.sample.controller.SampleItemController
import ru.surfstudio.android.recycler.extension.sample.controller.SampleStickyFooterItemController
import ru.surfstudio.android.recycler.extension.sample.controller.SampleStickyHeaderItemController
import ru.surfstudio.android.recycler.extension.sticky.*
import ru.surfstudio.android.recycler.extension.sticky.layoutmanager.StickyFooterListener
import ru.surfstudio.android.recycler.extension.sticky.layoutmanager.StickyHeaderListener

class MainActivity : AppCompatActivity() {

    private lateinit var stickyEasyAdapter: StickyEasyAdapter
    private val itemController = SampleItemController()
    private val stickyHeaderItemController = SampleStickyHeaderItemController()
    private val stickyFooterItemController = SampleStickyFooterItemController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stickyEasyAdapter = StickyEasyAdapter(activity_main_recycler,
                isVisibleFirstFooterAtLaunch = true)
        stickyEasyAdapter.stickyLayoutManager.setStickyHeaderListener(object : StickyHeaderListener {
            override fun headerAttached(headerView: View, adapterPosition: Int) {
                Log.d("LOG", "1111 headerAttached")
            }

            override fun headerDetached(headerView: View, adapterPosition: Int) {
                Log.d("LOG", "1111 headerDetached")
            }
        })

        stickyEasyAdapter.stickyLayoutManager.setStickyFooterListener(object : StickyFooterListener {
            override fun footerAttached(footerView: View, adapterPosition: Int) {
                Log.d("LOG", "1111 footerAttached")
            }

            override fun footerDetached(footerView: View, adapterPosition: Int) {
                Log.d("LOG", "1111 footerDetached")
            }
        })
        activity_main_recycler.adapter = stickyEasyAdapter

        val itemList = generateData()
        /*itemList.addStickyHeaderIf({ prev, next ->
            val prv = prev as? Data
            val nxt = next as? Data
            if (prv?.rank != nxt?.rank) {
                nxt?.rank
            } else {
                null
            }
        }, stickyHeaderItemController)*/
        /*itemList.addStickyFooterIf({ prev, next ->
            val prv = prev as? Data
            val nxt = next as? Data
            if (prv?.rank != nxt?.rank) {
                nxt?.rank
            } else {
                null
            }
        }, stickyFooterItemController)*/
        stickyEasyAdapter.setItems(itemList)
    }

    private fun generateData() = ItemList().apply {
        add(Data("Scooby-Doo", "Main characters"), itemController)
        add(Data("Shaggy Rogers", "Main characters"), itemController)
        add(Data("Fred Jones", "Main characters"), itemController)
        add(Data("Daphne Blake", "Main characters"), itemController)
        add(Data("Velma Dinkley", "Main characters"), itemController)
        add(Data("Scrappy-Doo", "Secondary characters"), itemController)
        add(Data("Scooby-Dum", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        addStickyHeader("Блок с заголовком", stickyHeaderItemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Yabba-Doo", "Secondary characters"), itemController)
        add(Data("Scooby-Dee", "Secondary characters"), itemController)
        add(Data("Vincent Van Ghoul", "Introduced in The 13 Ghosts of Scooby-Doo"), itemController)
        add(Data("Flim Flam", "Introduced in The 13 Ghosts of Scooby-Doo"), itemController)
        add(Data("Weerd and Bogel", "Introduced in The 13 Ghosts of Scooby-Doo"), itemController)
        add(Data("The Hex Girls", "Introduced in Scooby-Doo and the Witch\'s Ghost"), itemController)
        add(Data("Thorn", "Introduced in Scooby-Doo and the Witch\'s Ghost"), itemController)
        add(Data("Dusk", "Introduced in Scooby-Doo and the Witch\'s Ghost"), itemController)
        add(Data("Luna", "Introduced in Scooby-Doo and the Witch\'s Ghost"), itemController)
        add(Data("Red Herring", "Introduced in A Pup Named Scooby-Doo"), itemController)
        add(Data("Sheriff Bronson Stone", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Hot Dog Water", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        addStickyFooter("Блок с кнопками", stickyFooterItemController)
        add(Data("Mayor Fred Jones, Sr.", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Mayor Janet Nettles", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Mr. E (Ricky Owens)", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Angel Dynamite (Cassidy Williams)", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Professor Pericles", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Brad Chiles and Judy Reeves", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Nova", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("Alice May", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
        add(Data("The Evil Entity", "Introduced in Scooby-Doo! Mystery Incorporated"), itemController)
    }
}
