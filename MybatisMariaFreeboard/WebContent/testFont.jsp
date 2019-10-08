<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="axicon/axicon.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/ax5ui/ax5ui-toast/master/dist/ax5toast.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5core/master/dist/ax5core.min.js"></script>
<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5ui-toast/master/dist/ax5toast.min.js"></script>
<script type="text/javascript">
$(document.body).ready(function () {
    var toast = new ax5.ui.toast({
        containerPosition: "top-right",
        onStateChanged: function(){
            console.log(this);
        }
    });

    $('#toast-push').click(function () {
        toast.push('Toast message', function () {
            // close toast
            console.log(this);
        });
    });
});
</script>
</head>
<body>
<i class="axi axi-axisj"></i>
<i class="axi axi-axu"></i>
<i class="axi axi-axicon-o"></i>
<button id="toast-push">asdf</button>
</body>
</html>