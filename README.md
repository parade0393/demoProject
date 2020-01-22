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

      