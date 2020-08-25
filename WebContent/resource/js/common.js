console.clear();

// lib 시작 
String.prototype.replaceAll = function(org, dest) {
	return this.split(org).join(dest);
}

function getUriParams(uri) {
	uri = uri.trim();
	uri = uri.replaceAll('&amp;', '&');
	if (uri.indexOf('#') !== -1) {
		var pos = uri.indexOf('#');
		uri = uri.substr(0, pos);
	}

	var params = {};

	uri.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) {
		params[key] = value;
	});
	return params;
}

function jq_attr($el, attrName, elseValue) {
	var value = $el.attr(attrName);

	if (value === undefined || value === "") {
		return elseValue;
	}

	return value;
}

// lib 끝

function MobileSideBar__init() {
	$('.mobile-top-bar .btn-toggle-mobile-side-bar').click(function() {
		var $this = $(this);

		if ($this.hasClass('active')) {
			$this.removeClass('active');
			$('.mobile-side-bar').removeClass('active');
		} else {
			$this.addClass('active')
			$('.mobile-side-bar').addClass('active');
		}
	});
}

// 유튜브 플러그인 시작
function youtubePlugin() {
	toastui.Editor.codeBlockManager.setReplacer("youtube", function(youtubeId) {
		// Indentify multiple code blocks
		const wrapperId = "yt" + Math.random().toString(36).substr(2, 10);

		// Avoid sanitizing iframe tag
		setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);

		return '<div id="' + wrapperId + '"></div>';
	});
}

function renderYoutube(wrapperId, youtubeId) {
	const el = document.querySelector('#' + wrapperId);

	var uriParams = getUriParams(youtubeId);

	var width = '100%';
	var height = '100%';

	if (uriParams.width) {
		width = uriParams.width;
	}

	if (uriParams.height) {
		height = uriParams.height;
	}

	var maxWidth = 500;

	if (uriParams['max-width']) {
		maxWidth = uriParams['max-width'];
	}

	var ratio = '16-9';

	if (uriParams['ratio']) {
		ratio = uriParams['ratio'];
	}

	var marginLeft = 'auto';

	if (uriParams['margin-left']) {
		marginLeft = uriParams['margin-left'];
	}

	var marginRight = 'auto';

	if (uriParams['margin-right']) {
		marginRight = uriParams['margin-right'];
	}

	if (youtubeId.indexOf('?') !== -1) {
		var pos = youtubeId.indexOf('?');
		youtubeId = youtubeId.substr(0, pos);
	}

	el.innerHTML = '<div style="max-width:'
			+ maxWidth
			+ 'px; margin-left:'
			+ marginLeft
			+ '; margin-right:'
			+ marginRight
			+ ';" class="ratio-'
			+ ratio
			+ ' relative"><iframe class="abs-full" width="'
			+ width
			+ '" height="'
			+ height
			+ '" src="https://www.youtube.com/embed/'
			+ youtubeId
			+ '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>';
}
// 유튜브 플러그인 끝

// repl 플러그인 시작
function replPlugin() {
	toastui.Editor.codeBlockManager.setReplacer("repl", function(replUri) {
		var postSharp = "";
		if (replUri.indexOf('#') !== -1) {
			var pos = replUri.indexOf('#');
			postSharp = replUri.substr(pos);
			replUri = replUri.substr(0, pos);
		}

		if (replUri.indexOf('?') === -1) {
			replUri += "?dummy=1";
		}

		replUri += "&lite=true";
		replUri += postSharp;

		// Indentify multiple code blocks
		const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

		// Avoid sanitizing iframe tag
		setTimeout(renderRepl.bind(null, wrapperId, replUri), 0);

		return "<div id=\"" + wrapperId + "\"></div>";
	});
}

function renderRepl(wrapperId, replUri) {
	const el = document.querySelector(`#${wrapperId}`);

	var uriParams = getUriParams(replUri);

	var height = 400;

	if (uriParams.height) {
		height = uriParams.height;
	}

	el.innerHTML = '<iframe height="'
			+ height
			+ 'px" width="100%" src="'
			+ replUri
			+ '" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>';
}
// repl 플러그인 끝

// codepen 플러그인 시작
function codepenPlugin() {
	toastui.Editor.codeBlockManager
			.setReplacer(
					"codepen",
					function(codepenUri) {
						// Indentify multiple code blocks
						const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

						// Avoid sanitizing iframe tag
						setTimeout(renderCodepen.bind(null, wrapperId,
								codepenUri), 0);

						return '<div id="' + wrapperId + '"></div>';
					});
}

function renderCodepen(wrapperId, codepenUri) {
	const el = document.querySelector(`#${wrapperId}`);

	var uriParams = getUriParams(codepenUri);

	var height = 400;

	if (uriParams.height) {
		height = uriParams.height;
	}

	var width = '100%';

	if (uriParams.width) {
		width = uriParams.width;
	}

	if (!isNaN(width)) {
		width += 'px';
	}

	if (codepenUri.indexOf('#') !== -1) {
		var pos = codepenUri.indexOf('#');
		codepenUri = codepenUri.substr(0, pos);
	}

	el.innerHTML = '<iframe height="'
			+ height
			+ '" style="width: '
			+ width
			+ ';" scrolling="no" title="" src="'
			+ codepenUri
			+ '" frameborder="no" allowtransparency="true" allowfullscreen="true"></iframe>';
}
// repl 플러그인 끝

// lib 시작
String.prototype.replaceAll = function(org, dest) {
	return this.split(org).join(dest);
}

function getUriParams(uri) {
	uri = uri.trim();
	uri = uri.replaceAll('&amp;', '&');
	if (uri.indexOf('#') !== -1) {
		var pos = uri.indexOf('#');
		uri = uri.substr(0, pos);
	}

	var params = {};

	uri.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) {
		params[key] = value;
	});
	return params;
}
// lib 끝

$(function() {
	MobileSideBar__init();
});