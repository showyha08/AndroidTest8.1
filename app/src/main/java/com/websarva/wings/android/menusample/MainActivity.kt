package com.websarva.wings.android.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    // リストビューに表示するリストデータ
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

    //SimpleAdapterの第４引数fromに使用するプロパティ
    private val _from = arrayOf("name", "price")

    //SimpleAdapterの第5引数toに使用するプロパティ
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 定食メニューListオブジェクトをprivateメソッドを利用して用意し，プロパティに格納
        _menuList = createTeishokuList()
        // 画面部品ListViewを取得
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // SimpleAdapterを生成
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        // アダプタの登録
        lvMenu.adapter = adapter
        // リストタップのリスなクラス登録
        lvMenu.onItemClickListener = ListItemClickListener()

    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        // 定食メニューリスト用のListオブジェクトを用意
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf<String, Any>(
            "name" to "唐揚げ定食",
            "price" to 800,
            "desc" to "若鶏の唐揚にサラダ、ご飯とお味噌汁が付きます"
        )
        menuList.add(menu)
        menu = mutableMapOf(
            "name" to "ハンバーグ定食",
            "price" to 850,
            "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。"
        )
        menuList.add(menu)
        return menuList
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた行のデータを取得
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            //定食名と金額取得
            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int

            val intent2MenuThanks =
                Intent(this@MainActivity, MenuThanksActivity::class.java).apply {
                    putExtra("menuName", menuName)
                    putExtra("menuPrice", "${menuPrice}円")
                }

            //第２画面の起動
            startActivity(intent2MenuThanks)
        }
    }


}