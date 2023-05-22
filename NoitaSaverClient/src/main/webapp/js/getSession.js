// 定义一个名为 getUserData 的函数，用于获取用户数据并存入Vue变量
function getUserData(vue, errorstr) {
	$.ajax({
		url: "getSession",
		dataType: "JSON",
		data: {
			key: "user"
		},
		success(result) {
			vue.$message({
				message: errorstr + '成功',
				type: 'success'
			});
			location.href = 'index.html';
		},
		error() {
			vue.$message.error(errorstr + '失败');
		}
	});
}
