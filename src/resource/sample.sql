drop database if exists netFind;
create database netFind;
use netFind;

create table user (
	uno int auto_increment ,
    constraint primary key (uno) ,
    uid varchar(15) not null unique ,
    upwd varchar(15) not null,
    uphone varchar(15) not null unique,
	uname varchar(10) not null unique,
    ubname varchar(30) not null,
    ubnumber varchar(15) not null unique,
    ublocation longtext not null
);

create table sample(
	sno int auto_increment,
	constraint primary key (sno),
    sname varchar(50) not null,
    sspec varchar(20) not null,
	smaker varchar(30) not null,
	sunit varchar(30) not null
);

create table product(
	pno int auto_increment,
	constraint primary key (pno) ,
	sno int ,
    constraint foreign key (sno) references sample (sno),
    uno int ,
    constraint foreign key (uno) references user (uno),
    pprice int not null , 
    pstock int not null ,
	pstatus boolean default false
);

create table note(
	nno int auto_increment,
	constraint primary key (nno),
    nsend int,
    constraint foreign key (nsend) references user (uno),
    nreceive int,
	constraint foreign key (nreceive) references user (uno),
    ncontext longtext not null,
	ndate datetime default now()
);

-- user 테이블 샘플 데이터
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('admin', '1234', '010-1234-5678', '관리자1', '네트파인더', '123-45-46443', '서울시 도봉구 창동 7번지');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('kafell', '141543', '010-1111-1111', '아저씨1', '행복상회', '231-53-45345', '경기도 부천시 상동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('ubtech', 'pw123', '010-2222-2222', '가나전력', '유비전력', '111-22-33333', '서울 성동구 동일로 23');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user1', 'pass1', '010-0001-0001', '김철수', '철수네가게', '101-01-00001', '서울시 강남구 역삼동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user2', 'pass2', '010-0002-0002', '이영희', '영희네상점', '102-02-00002', '부산시 해운대구 우동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user3', 'pass3', '010-0003-0003', '박지성', '지성상사', '103-03-00003', '대구시 수성구 범어동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user4', 'pass4', '010-0004-0004', '최민호', '민호유통', '104-04-00004', '인천시 남동구 구월동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user5', 'pass5', '010-0005-0005', '정수빈', '수빈상회', '105-05-00005', '광주시 서구 치평동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user6', 'pass6', '010-0006-0006', '강하나', '하나마트', '106-06-00006', '대전시 유성구 봉명동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user7', 'pass7', '010-0007-0007', '조현우', '현우전기', '107-07-00007', '울산시 남구 신정동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user8', 'pass8', '010-0008-0008', '윤보라', '보라상사', '108-08-00008', '세종시 보람동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user9', 'pass9', '010-0009-0009', '임창정', '창정유통', '109-09-00009', '경기도 수원시 팔달구 인계동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user10', 'pass10', '010-0010-0010', '백지영', '지영마트', '110-10-00010', '강원도 춘천시 퇴계동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user11', 'pass11', '010-0011-0011', '송강호', '강호전기', '111-11-00011', '충청북도 청주시 흥덕구 가경동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user12', 'pass12', '010-0012-0012', '전지현', '지현상회', '112-12-00012', '충청남도 천안시 서북구 불당동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user13', 'pass13', '010-0013-0013', '이병헌', '병헌상사', '113-13-00013', '전라북도 전주시 완산구 효자동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user14', 'pass14', '010-0014-0014', '김혜수', '혜수유통', '114-14-00014', '전라남도 목포시 상동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user15', 'pass15', '010-0015-0015', '황정민', '정민마트', '115-15-00015', '경상북도 포항시 남구 대이동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user16', 'pass16', '010-0016-0016', '하정우', '정우전기', '116-16-00016', '경상남도 창원시 성산구 상남동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user17', 'pass17', '010-0017-0017', '마동석', '동석상사', '117-17-00017', '제주도 제주시 연동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user18', 'pass18', '010-0018-0018', '유해진', '해진유통', '118-18-00018', '서울시 마포구 서교동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user19', 'pass19', '010-0019-0019', '류승룡', '승룡마트', '119-19-00019', '경기도 고양시 일산서구 주엽동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user20', 'pass20', '010-0020-0020', '오달수', '달수전기', '120-20-00020', '인천시 연수구 송도동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user21', 'pass21', '010-0021-0021', '곽도원', '도원상회', '121-21-00021', '대구시 달서구 상인동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user22', 'pass22', '010-0022-0022', '조진웅', '진웅상사', '122-22-00022', '부산시 부산진구 부전동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user23', 'pass23', '010-0023-0023', '이성민', '성민유통', '123-23-00023', '울산시 중구 옥교동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user24', 'pass24', '010-0024-0024', '설경구', '경구마트', '124-24-00024', '광주시 동구 충장로');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user25', 'pass25', '010-0025-0025', '송중기', '중기전기', '125-25-00025', '대전시 서구 둔산동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user26', 'pass26', '010-0026-0026', '박보검', '보검상사', '126-26-00026', '서울시 종로구 삼청동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user27', 'pass27', '010-0027-0027', '공유', '공유유통', '127-27-00027', '경기도 용인시 수지구 풍덕천동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user28', 'pass28', '010-0028-0028', '현빈', '현빈마트', '128-28-00028', '강원도 강릉시 교동');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user29', 'pass29', '010-0029-0029', '원빈', '원빈전기', '129-29-00029', '충청북도 제천시 중앙로');
INSERT INTO user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) VALUES ('user30', 'pass30', '010-0030-0030', '장동건', '동건상회', '130-30-00030', '충청남도 보령시 대천동');

-- sample 테이블 샘플 데이터
INSERT INTO sample (sname, sspec, smaker, sunit) VALUES
('UTP케이블', 'CAT.5E', 'LS전선', 'BOX'),
('UTP케이블', 'CAT.6', '가온전선', 'BOX'),
('UTP케이블', 'CAT.6A', '대원전선', 'BOX'),
('FTP케이블', 'CAT.5E', '벨덴', 'BOX'),
('FTP케이블', 'CAT.6', 'AMP', 'BOX'),
('STP케이블', 'CAT.6A', '넥상스', 'BOX'),
('광케이블', '싱글모드 2C', '대한광통신', 'M'),
('광케이블', '싱글모드 4C', '유비쿼스', 'M'),
('광케이블', '멀티모드 4C', '오이솔루션', 'M'),
('광케이블', '멀티모드 8C', '코위버', 'M'),
('RJ-45 플러그', 'CAT.5E', '3M', 'EA'),
('RJ-45 플러그', 'CAT.6', '판듀잇', 'EA'),
('RJ-45 잭', 'CAT.5E', '타이코', 'EA'),
('RJ-45 잭', 'CAT.6', '레비톤', 'EA'),
('패치판넬', '24포트 CAT.5E', '시몬', 'EA'),
('패치판넬', '48포트 CAT.6', '리더스', 'EA'),
('허브', '5포트 10/100M', 'ipTIME', 'EA'),
('허브', '8포트 기가', '넷기어', 'EA'),
('허브', '16포트 기가', '디링크', 'EA'),
('허브', '24포트 기가', '시스코', 'EA'),
('케이블타이', '100mm 백색', '국산', '봉'),
('케이블타이', '200mm 흑색', '국산', '봉'),
('PVC덕트', '1호 회색', '화성', 'M'),
('PVC덕트', '2호 백색', '중앙', 'M'),
('PVC덕트', '3호 아이보리', '경동', 'M'),
('아울렛', '1구', '나노', 'EA'),
('아울렛', '2구', '르그랑', 'EA'),
('아울렛', '4구', '진흥', 'EA'),
('몰딩', '바닥몰딩 1호', '신성', 'M'),
('몰딩', '알루미늄몰딩 2호', '세명', 'M');

-- product 테이블 샘플 데이터
INSERT INTO product (sno, uno, pprice, pstock, pstatus) VALUES
(1, 4, 100000, 10, true), (2, 4, 120000, 5, true), (3, 4, 150000, 3, false),
(4, 5, 110000, 8, true), (5, 5, 130000, 4, true), (6, 5, 160000, 2, false),
(7, 6, 500, 100, true), (8, 6, 600, 200, true), (9, 6, 700, 150, false),
(10, 7, 800, 50, true), (11, 7, 1000, 30, true), (12, 7, 1200, 20, false),
(13, 8, 1500, 50, true), (14, 8, 1800, 40, true), (15, 8, 2000, 30, false),
(16, 9, 25000, 10, true), (17, 9, 30000, 8, true), (18, 9, 35000, 5, false),
(19, 10, 15000, 20, true), (20, 10, 20000, 15, true), (21, 10, 25000, 10, false),
(22, 11, 3000, 100, true), (23, 11, 4000, 80, true), (24, 11, 5000, 60, false),
(25, 12, 1000, 200, true), (26, 12, 1200, 150, true), (27, 12, 1500, 100, false),
(28, 13, 2000, 50, true), (29, 13, 2500, 40, true), (30, 13, 3000, 30, false);

-- note 테이블 샘플 데이터
INSERT INTO note (nsend, nreceive, ncontext) VALUES
(4, 5, '안녕하세요, UTP케이블 CAT.5E 재고 문의드립니다.'),
(5, 4, '네, 현재 10박스 재고 있습니다.'),
(6, 7, '광케이블 싱글모드 2C, 100M 가능한가요?'),
(7, 6, '네, 가능합니다. 견적 보내드리겠습니다.'),
(8, 9, '패치판넬 24포트 CAT.5E 5개 주문합니다.'),
(9, 8, '알겠습니다. 내일 출고됩니다.'),
(10, 11, '허브 5포트 10/100M 제품 단가 문의합니다.'),
(11, 10, '개당 15,000원입니다.'),
(12, 13, '케이블타이 100mm 백색, 10봉지 주문합니다.'),
(13, 12, '주문 확인되었습니다. 감사합니다.'),
(14, 15, 'PVC덕트 1호 회색, 50M 필요합니다.'),
(15, 14, '네, 재고 충분합니다. 방문수령 가능하신가요?'),
(16, 17, '아울렛 2구 100개 재고 있나요?'),
(17, 16, '네, 현재 200개 재고 보유중입니다.'),
(18, 19, '몰딩 바닥몰딩 1호, 20M 주문합니다.'),
(19, 18, '알겠습니다. 배송지 주소 알려주세요.'),
(20, 21, 'RJ-45 플러그 CAT.6, 100개 가격 문의'),
(21, 20, '100개 한박스에 12,000원입니다.'),
(22, 23, 'FTP케이블 CAT.6, 1박스 주문합니다.'),
(23, 22, '네, 주문 접수되었습니다.'),
(24, 25, '광케이블 멀티모드 8C, 재고 및 단가 문의'),
(25, 24, 'M당 800원이고, 재고는 500M 있습니다.'),
(26, 27, '허브 24포트 기가, 2대 주문합니다.'),
(27, 26, '알겠습니다. 바로 발송해드리겠습니다.'),
(28, 29, 'PVC덕트 3호 아이보리, 30M 주문'),
(29, 28, '네, 확인했습니다.'),
(30, 4, 'UTP케이블 CAT.6A, 2박스 주문합니다.'),
(4, 30, '주문 감사합니다. 내일 도착 예정입니다.'),
(5, 6, 'STP케이블 CAT.6A 재고 문의'),
(6, 5, '현재 재고 소진 상태입니다. 다음주 입고 예정입니다.');

select * from user;
select * from sample;
select * from product;
select * from note;
