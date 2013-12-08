Using RMI basic DEMO

Server 截图，图片为 BufferedImage ，将 BufferedImage 转化为 Byte 数据流，传输Byte数据，端口号为1099
Client 图形界面，输入框输入 rmi://127.0.0.1/connect 点击 "Click the button" 得到Server传过来的图片，点击 "Click the end program" 关闭程序，Client得到的是 ByteArrayInputStream 二进制 Byte 数据流，然后转化为 BufferedImage 接着显示在窗口上，显示同时将图片保存在本地。若本地有该图片，将覆盖该图片。