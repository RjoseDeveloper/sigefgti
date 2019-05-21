<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A layout example that shows off a responsive product landing page.">
        <title>FGTI</title>

        <link rel="stylesheet" href="css/layouts/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="icon" type="icon/png" href="img/ucm.png" />
        <!--[if lte IE 8]>
            <link rel="stylesheet" href="css/layouts/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="css/layouts/grids-responsive-min.css">
        <!--<![endif]-->

        <!--[if lte IE 8]>
            <link rel="stylesheet" href="css/layouts/marketing-old-ie.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="css/layouts/marketing.css">
        <!--<![endif]-->
    </head>
    <body>
        <jsp:include page="layout/header_admin.jsp"/>
       
        <iframe src="main.jsp" width="100%" height="700" scrolling="yes"  name="main" style="border: none;"></iframe>
        <jsp:include page="layout/footer.html"/>
    </body>
</html>

