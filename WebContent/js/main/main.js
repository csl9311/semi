
  var login; // 데이터 가져 올 부분

  // 로그인 버튼 이벤트
  $('.login').on('click', function(){
    login=true;
    if(login){
      $('.login').css('display', 'none');
      $('.login-btn').css('display','inline-block');
/*      $('.profile').css('display','inline-block');
      $('.logout').css('display','inline-block');*/
    }
  });

  // 로그아웃 버튼 이벤트
  $('.logout').on('click',function(){
    login = false;
    if(!login){
      $('.login').css('display', 'inline-block');
      $('.logout').css('display','none');
      $('.profile').css('display','none');
    }
  });

  // 대분류 영역 토글
  $('#cateImg').on('click', function(){
    $('.menu').slideToggle(300);
  });

  // 중분류 영역 토글
  $('#lCate').on('click', function(){
    $('#mCate').slideToggle(300);
  });

  // 검색 결과 출력
  var data = [1,2,3,4,5];
  $('#search').on('click', function(){
    $('.result').html("");
    for(var i = 0; i < data.length; i ++){
      $('.result').append(
        "<br>" +  data[i] + "번 째 데이터"
      );
    }
  });
  
  


