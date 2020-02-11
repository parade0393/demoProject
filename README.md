#### demoProject

  android常见UI效果的练习和学习积累

  1. ConstraintLayout

     1. 权重，比如竖方向的权重。

        ```xml
        <!--使用权重要有完整的约束 比如这里竖直方向的权重,必须同时约束top和bottom-->
        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recycler_base"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintVertical_weight="1"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/rl_common_title"/>
        ```

2.  RecyclerView
   1. 知识点
      1. `getChildLayoutPosition`和`getChildAdapterPosition`
      2. 画分割线时`drawRect`和`drawRect`的区别
   2. 通讯录
      1. 第一步普通的列表
         * 关键点1，汉字转拼音(首字母)，利用开源库 https://github.com/promeG/TinyPinyin
         * 关键点2，针对转换后的数据排序`Collections.sort()`
      2. 第二部画分组头部和分割线
         1. 关键点1：判断是不是每组的第一个，如果是就留大距离
         2. 关键点2：根据是不是每组的第一个决定画分割线或者头部分组名称
      3. 第三步画悬浮框
         1. 关键点1：第一个可见的item的position，悬浮框遮挡是否可见
         2. 然后画头部分组名称一样
      4. 第四步画侧边栏
         * 关键点1：代码改变shape特征   

