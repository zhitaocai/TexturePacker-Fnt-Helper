# TexturePacker-Fnt-Helper

## 项目说明

将字体位图图片和正常游戏精灵图片，通过TexturePacker打包为一个图集的时候，一般会输出

* `*.png`: 合并后的图集图片
* `*.plist`: 一个描述如何从图集图片中获取到原始图片的描述性文件

而原始字体的位图图片是由下面两个文件组成

* `*.png`: 位图的图集图片
* `*.fnt`: 一个描述如何从位图图集图片中获取到原始文字的描述性文件

因为合并之后，字体位图图片一般会发生偏移，因此通过原来的 `*.fnt` 文件，将不能在合并后的图集图片中获取到正确的文字位置，因为合并后的字体图集图片在新的图集中，位置是发生了偏移的，可能不在是 (0, 0) 作为其起点坐标了。

本工具为修正 `*.fnt` 文件得出一个新的修正偏移量的 `*_fix.fnt` 文件，通过这个新的 `*_fix.fnt` 文件就可以从新的图集图片中正确解析出位图文字


## 使用说明

1. 安装 `Java 8+` 
2. 下载本项目
   
    ```
    git clone git@github.com:zhitaocai/TexturePacker-Fnt-Helper.git
    ```
3. 进入本项目目录执行下面命令即可

    ```java
    java -jar FntHelper.jar xxx/xxx.fnt 100 100
    
    * 参数一：fnt原始文件路径
    * 参数二：x偏移量（暂时需要自己查看 .plist 文件去获取）
    * 参数三：y偏移量（暂时需要自己查看 .plist 文件去获取）
    ```
    
    执行上述命令之后会在原来的 `*.fnt` 文件同目录下生成一个新的 `*_fix.fnt` 文件
    
    
##  参考文献

* [fnt文件格式](http://www.angelcode.com/products/bmfont/doc/file_format.html)
* [ASCII Code](https://zh.wikipedia.org/wiki/ASCII)
* [TexturePacker使用介绍](https://www.codeandweb.com/texturepacker/documentation)
* [TexturePacker输出数据结构说明](https://www.codeandweb.com/texturepacker/documentation/custom-exporter)

## 可能有帮助的小项目

* [XMLToFnt](shttps://github.com/aiekick/XMLToFnt)

## 其他

* [通过 Bitmap Font Generator 生成 fnt 与 png 文件供 cocos2d-x 中 LabelBMFont 使用达到以图片表现数字](https://blog.csdn.net/song_hui_xiang/article/details/44022419)
