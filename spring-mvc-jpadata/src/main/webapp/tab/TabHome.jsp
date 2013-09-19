<html lang="en">
<head>
<meta charset="utf-8" />
<title>jQuery UI Tabs - Default functionality</title>
<script src="jquery-1.9.1.js"></script>
<script src="jquery-ui.js"></script>
<link rel="stylesheet" href="jquery-ui.css" />
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>

	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Nunc tincidunt</a></li>
			<li><a href="#tabs-2">Proin dolor</a></li>
			<li><a href="#tabs-3">Aenean lacinia</a></li>
		</ul>
		<div id="tabs-1">
			<p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a,
				tempus lectus.</p>
		</div>
		<div id="tabs-2">
			<p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus
				gravida ante, ut pharetra massa metus id nunc. Duis scelerisque
				felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris
				consectetur tortor et purus.</p>
		</div>
		<div id="tabs-3">
			<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse
				potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque
				lacus.</p>
			<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at,
				Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus
				hendrerit hendrerit.</p>
		</div>
	</div>


</body>
</html>