fun LCSGenerator(a: String, b: String): Int {
	val arr1 = a.toCharArray()
	val arr2 = b.toCharArray()
	
	val memo = Array<Array<Int>>(arr2.size+1, {
		i->Array<Int>(arr1.size+1, {j->0})
	})
	
	// 각 문자열에 대해 루프를 돌면서 공통 유무 확인
	// 루프를 돌다 공통된 문자가 나오기만 하면 +1 한다
	var maxval = 0
	var i = 1
	for(b in arr2) {
		var j = 1
		for(a in arr1) {
			if(a == b) {
				memo[i][j] = memo[i-1][j-1] + 1
			}
			else {
				memo[i][j] = Math.max(memo[i][j-1], memo[i-1][j])
			}
			
			if(memo[i][j] > maxval) maxval = memo[i][j]
			j++
		}
		i++
	}
	
	print("X ")
	for(x in 0..arr1.size-1) {
		print(arr1[x]+" ")
	}
	println()
	
	for(x in 0..memo.size-1) {
		for(y in 0..memo[x].size-1) {
			print(memo[x][y].toString()+' ')
		}	
		if(x == 0) print("X")
		else print(arr2[x-1])
		println()
	}
	
	// 여기서 완성된 LCS를 뽑아낸 후 a와 비교하여 글자를 추가하는 방법의 수를 찾아야 함
	// LCS는 각 숫자가 시작되는 꼭지점을 찾아가서 가져오면 된다
	var cx = arr1.size
	var cy = arr2.size
	var current = memo[cy][cx]
	var lcs = StringBuilder()
	while(current != 0) {
		if(memo[cy-1][cx] == memo[cy][cx-1]) {
			// 확인됨
			println("POS: "+cx+" "+cy+" "+arr1[cx-1])
			lcs.insert(0, arr1[cx-1])
			cx--
			cy--
		}
		else if(memo[cy-1][cx] > memo[cy][cx-1]) {
			cy--
		}
		else if(memo[cy-1][cx] < memo[cy][cx-1]) {
			cx--
		}
		else {
			cx--
			cy--
		}
		current = memo[cy][cx]
	}
	
	println("LCS: "+lcs.toString())
	
	return maxval
}