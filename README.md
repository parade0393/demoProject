此项目是用来验证各种UI效果的demo

ViewPager搭配Fragment声明周期
FragmentPagerAdapter和FragmentStatePageAdapter的instantiateItem调用时机都一样
PagerAdapter的notifyDataSetChanged源码分析
    ```
        /**
         * This method should be called by the application if the data backing this adapter has changed
         * and associated views should update.
         */
        public void notifyDataSetChanged() {
            synchronized (this) {
                if (mViewPagerObserver != null) {
                    mViewPagerObserver.onChanged();
                }
            }
            mObservable.notifyChanged();
        }
    ```
    以上有两个变量：
    ```
    private final DataSetObservable mObservable = new DataSetObservable();
    private DataSetObserver mViewPagerObserver;
    ```
    ViewPager的刷新过程：
    调用notifyDataSetChanged后辉调用ViewPager的`dataSetChanged()`方法，该方法会遍历ViewPager所有的item(由缓存的item数量决定，默认为当前页和其左右加起来共3页，这个可以自行设定，但是至少会缓存2页)
    为每个 Item 返回一个状态值（POSITION_NONE/POSITION_UNCHANGED），如果是 POSITION_NONE，那么该 Item 会被 destroyItem(ViewGroup container, int position, Object object) 方法 remove 掉，
    然后重新加载，如果是 POSITION_UNCHANGED，就不会重新加载，默认是 POSITION_UNCHANGED，
    所以如果不重写 getItemPosition(Object object)，修改返回值，就无法看到 notifyDataSetChanged() 的刷新效果。
