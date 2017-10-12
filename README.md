# MyBleTools
// BLE 蓝牙4.0
操作依赖
	maven { url 'https://jitpack.io' }
  
   compile 'com.github.meihuali:MyBleTools:1.0.0' 
   
   第一步操作动态授权 蓝牙权限 这里不做过讲解 看demo  因为太容易
   
   第2步就是核心了·首先 就是判断本手机带不带蓝牙然后判断蓝牙有没有打开
   没有打开就去开启然后启动  扫描的核心的函数，如果第2次启动APP 就直接去
   启动核心 扫描的函数
   这里分两步 第一步就是 扫描核心函数 
       //这句话是搜索蓝牙设备
       
          MySearchDivce.startSearchDevice(MainActivity.this);
                        //这个接口回调是吧搜索到的设备回调到这个Activity 中来
                        MySearchDivce.getSearchDevices(new MySearchDivce.onDivceListenr() {
                            @Override
                            public void searchRelust(List<Device> mlist) {
                                L.e("搜到的设备 "+mlist.size());
                                //设置adapter
                                initAdapter(mlist);
                            }
                        });
 到此为止就表示搜索完毕以及 最终结果为一个集合 详细请看 demo
 
 接下来就是 连接设备了·
 
  // 这句话就是核心连接蓝牙设备的代码
  
  MySearchDivce.ConnectDivce(getApplicationContext(),macaddress);
   
   
   
   
   
