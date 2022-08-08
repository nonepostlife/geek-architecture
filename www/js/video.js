(function() {
	console.log(123);
	var video = document.getElementById('video'),
		vendorUrl = window.URL || window.webkitURL;

	navigator.getMedia = 	navigator.getUserMedia ||
							navigator.webkitGetUserMedia ||
							navigator.mozGetUserMedia ||
							navigator.msGetUserMedia;

	navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;
	if (navigator.getUserMedia) {
		navigator.getUserMedia({
			video: true
		}, (stream) => {
			try {
				video.srcObject = stream;
			} catch (error) {
				video.srcObject = window.URL.createObjectURL(stream);
			}
			video.play();
		}, (error) => {
			console.log(error);
		});
	}

})();