# SemicircleProgress
两个自定义圆形和弧形进度条
===

![image](https://github.com/ppg408331701/SemicircleProgress/blob/master/img/img3.gif)  


### 第一个SemicircleProgress可以对中间和下面的字自定义
### 第二个CircularProgressar可以对文字部分定义,对图片自定义,显示或隐藏,与详细需要请直接拖到本地自己修改

### SemicircleProgress
<table>
  <tdead>
    <tr>
      <th align="center">配置参数</th>
      <th align="center">参数含义</th>
    </tr>
  </tdead>
  <tbody>
    <tr>
      <td align="center">semicircleSize</td>
      <td align="center">半弧形的半径大小,默认100dp</td>
    </tr>
    <tr>
      <td align="center">semicirclebackgroundLineColor</td>
      <td align="center">半弧形的背景线颜色,默认灰色</td>
    </tr>
    <tr>
      <td align="center">semicirclefrontLineColor</td>
      <td align="center">半弧形的前景线颜色,默认橘黄色</td>
    </tr>
    <tr>
      <td align="center">semicirclelineSize</td>
      <td align="center">半弧形的线宽度,默认3dp</td>
    </tr>
    <tr>
      <td align="center">semicirclesubtitleColor</td>
      <td align="center">副标题字体颜色</td>
    </tr>
    <tr>
      <td align="center">semicirclesubtitleSize</td>
      <td align="center">副标题字体大小</td>
    </tr>
    <tr>
      <td align="center">semicirclesubtitleText</td>
      <td align="center">副标题文本</td>
    </tr>
    <tr>
      <td align="center">semicircletitleColor</td>
      <td align="center">标题字体颜色</td>
    </tr>
    <tr>
      <td align="center">semicircletitleSize</td>
      <td align="center">标题字体大小</td>
    </tr>
    <tr>
      <td align="center">semicircletitleText</td>
      <td align="center">标题文本</td>
    </tr>
  </tbody>
</table>

### CircularProgressar
<table>
  <tdead>
    <tr>
      <th align="center">配置参数</th>
      <th align="center">参数含义</th>
    </tr>
  </tdead>
  <tbody>
    <tr>
      <td align="center">circularLeftText</td>
      <td align="center">左边文本</td>
    </tr>
    <tr>
      <td align="center">circularLeftTextColor</td>
      <td align="center">左边字体颜色</td>
    </tr>
    <tr>
      <td align="center">circularLeftTextSize</td>
      <td align="center">左边字体大小</td>
    </tr>
    <tr>
      <td align="center">circularRightText</td>
      <td align="center">右边文本</td>
    </tr>
    <tr>
      <td align="center">circularRightTextColor</td>
      <td align="center">右边字体颜色</td>
    </tr>
    <tr>
      <td align="center">circularRightTextSize</td>
      <td align="center">右边字体大小</td>
    </tr>
    <tr>
      <td align="center">circularSize</td>
      <td align="center">圆的大小</td>
    </tr>
    <tr>
      <td align="center">circularbackgroundLineColor</td>
      <td align="center">圆形的背景线颜色</td>
    </tr>
    <tr>
      <td align="center">circularfrontLineColor</td>
      <td align="center">圆形的前景线颜色</td>
    </tr>
    <tr>
      <td align="center">circularcentreBitmap</td>
      <td align="center">中间的颜色</td>
    </tr>
    <tr>
      <td align="center">circularlineSize</td>
      <td align="center">线的宽度</td>
    </tr>
    <tr>
      <td align="center">circularshowBitmap</td>
      <td align="center">是否显示中间的图片</td>
    </tr>
  </tbody>
</table>

### 在xml中使用

```java
	<simple.ppg.com.ppgsemicircle.Views.SemicircleProgressView
            android:id="@+id/semicircleProgressView"
            android:layout_width="match_parent"
            android:layout_height="150dp"
            app:semicircleSize="140dp"
            app:semicirclebackgroundLineColor="@color/colorPrimary"
            app:semicirclefrontLineColor="@color/colorAccent"
            app:semicirclelineSize="3dp"
            app:semicirclesubtitleColor="@color/colorPrimary"
            app:semicirclesubtitleSize="15sp"
            app:semicirclesubtitleText="integral"
            app:semicircletitleColor="@color/colorAccent"
            app:semicircletitleSize="20sp"
            app:semicircletitleText="积分" />
```

```java
	 <simple.ppg.com.ppgsemicircle.Views.CircularProgressar
            android:id="@+id/sesame_view"
            android:layout_width="270dp"
            android:layout_height="150dp"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            app:circularLeftText="50%"
            app:circularLeftTextColor="@color/colorPrimary"
            app:circularLeftTextSize="14sp"
            app:circularRightText="50/100"
            app:circularRightTextColor="@color/colorPrimary"
            app:circularRightTextSize="14sp"
            app:circularSize="120dp"
            app:circularbackgroundLineColor="@color/colorPrimary"
            app:circularcentreBitmap="@mipmap/icon_1"
            app:circularfrontLineColor="@color/colorAccent"
            app:circularlineSize="3dp"
            app:circularshowBitmap="true" />
```
