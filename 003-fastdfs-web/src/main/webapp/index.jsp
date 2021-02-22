<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"  %>
<html>
<body>
<h2>Hello World!</h2>
<h3>文件上传</h3>

<form action="fastDfs/uploadFile" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile"/><br/>

    <input type="submit" value="提交"/>

</form>

</body>
</html>
