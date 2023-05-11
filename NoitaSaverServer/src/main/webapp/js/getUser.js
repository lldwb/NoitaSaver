var getUser = () => {
	$.ajax({
		url: "getSession",
		dataType: "JSON",
		success(result) {
			return result;
		}
	})
}
