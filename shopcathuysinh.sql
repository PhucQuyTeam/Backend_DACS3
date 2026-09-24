-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3307 :3307
-- Thời gian đã tạo: Th9 24, 2026 lúc 09:04 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopcathuysinh`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `proviceId` int(11) NOT NULL,
  `wardId` int(11) NOT NULL,
  `streetDetail` varchar(100) NOT NULL,
  `userId` int(11) NOT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `address`
--

INSERT INTO `address` (`id`, `proviceId`, `wardId`, `streetDetail`, `userId`, `create_at`, `updated_at`) VALUES
(14, 8, 14, '1111', 44, '2025-12-18 17:00:00', NULL),
(15, 8, 14, 'dsfsd', 44, '2025-12-19 17:00:00', NULL),
(20, 1, 2, 'dsfds', 52, '2026-05-08 14:03:53', NULL),
(21, 1, 2, '21321', 52, '2026-05-08 14:05:17', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `blogs`
--

CREATE TABLE `blogs` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `blogs`
--

INSERT INTO `blogs` (`id`, `title`, `description`, `content`, `image`, `category_id`, `created_at`) VALUES
(20, 'Cá Ông Tiên (Angelfish): Vẻ đẹp kiêu sa từ dòng sông Amazon', 'Thường bị nhầm là hiền lành vì dáng bơi khoan thai, nhưng Ông Tiên thực chất là những kẻ săn mồi đáng gờm. Hướng dẫn nuôi và ép đẻ dòng cá huyền thoại này.', '<p>Trước khi cá Dĩa trở nên phổ biến, <strong>Cá Ông Tiên (Pterophyllum Scalare)</strong> mới chính là vua của bể thủy sinh. Với dáng bơi lướt nhẹ như một chiếc lá khô và bộ vây dài thướt tha, chúng mang lại vẻ đẹp tĩnh lặng và sang trọng cho bất kỳ bể cá nào.</p>\r\n\r\n    <h2>1. Đặc điểm nhận dạng và Tập tính</h2>\r\n    <p>Cá Ông Tiên thuộc họ Cichlid (Cá Rô phi), nghĩa là chúng khá hung dữ và có tính lãnh thổ cao. \r\n    <br>Hình dáng của chúng dẹt mỏng theo chiều ngang (để lách qua các khe rễ cây) và vây lưng, vây bụng kéo dài theo chiều dọc. Vì vậy, bể nuôi Ông Tiên cần có <strong>chiều cao tối thiểu 50cm</strong> để cá không bị \"gù\" vây.</p>\r\n    <p><strong>Lưu ý quan trọng:</strong> Dù miệng trông nhỏm, nhưng Ông Tiên là loài săn mồi (Predator). Tuyệt đối không nuôi chung với các loại cá quá nhỏ như Cá Trâm, Tép màu... vì chúng sẽ trở thành bữa ăn nhẹ cho Ông Tiên chỉ trong một đêm.</p>\r\n\r\n    <h2>2. Các dòng Ông Tiên phổ biến</h2>\r\n    <ul>\r\n        <li><strong>Ông Tiên Ai Cập (Altum):</strong> \"Chén thánh\" của người chơi. Kích thước khổng lồ, sọc đen rõ nét, rất khó thuần dưỡng và giá cực đắt.</li>\r\n        <li><strong>Ông Tiên Koi:</strong> Đỉnh đầu màu cam đỏ, thân trắng lốm đốm đen, nhìn như cá Koi Nhật Bản.</li>\r\n        <li><strong>Ông Tiên Đen (Black Lace):</strong> Toàn thân đen tuyền ma mị, rất nổi bật trên nền cây xanh.</li>\r\n        <li><strong>Ông Tiên Platinum:</strong> Trắng toát toàn thân, vây ánh bạc lấp lánh.</li>\r\n    </ul>\r\n\r\n    <h2>3. Sinh sản và Chăm sóc cá con</h2>\r\n    <p>Khác với đa số loài cá đẻ trứng rồi bỏ đi, Ông Tiên là những ông bố bà mẹ tuyệt vời.</p>\r\n    <h3>Dấu hiệu sắp đẻ:</h3>\r\n    <p>Cặp cá trống mái sẽ tách đàn, hung dữ đuổi các con khác đi. Chúng sẽ chọn một bề mặt phẳng (như vách kính, lá cây to, hoặc giá thể gốm) và liên tục rỉa sạch nó. Cá mái đẻ trứng dính lên đó và cá trống bơi theo thụ tinh.</p>\r\n    <h3>Chăm sóc:</h3>\r\n    <p>Trong quá trình ấp trứng (2-3 ngày), cá bố mẹ sẽ liên tục quạt nước để cung cấp oxy cho trứng. Khi cá con nở, chúng sẽ bảo vệ đàn con cực gắt. <br>\r\n    <em>Mẹo:</em> Nếu muốn giữ số lượng cá con cao, bạn nên tách trứng ra ấp riêng và cho cá bột ăn Artemia ấp nở, vì trong môi trường bể cộng đồng, cá con rất dễ bị các loài khác ăn thịt.</p>', 'ca_ong_tien.jpg', 3, '2025-12-01 11:24:32'),
(25, 'Kỹ thuật Hardscape: Xử lý Lũa ra màu và bí kíp dán đá siêu dính', 'Làm sao để nước không bị vàng khi chơi lũa? Cách dùng keo 502 và giấy ăn để tạo nên những bộ bố cục thách thức trọng lực.', '<p>Trong bộ môn thủy sinh, \"Hardscape\" (phần cứng) bao gồm Lũa và Đá chính là bộ khung xương của cả bể. Cây có thể thay đổi, nhưng khung xương thì cố định. Vì vậy, setup hardscape chuẩn ngay từ đầu là cực kỳ quan trọng.</p>\r\n\r\n    <h2>1. Xử lý Lũa không bị ra màu (Tiết ra Tanin)</h2>\r\n    <p>Nỗi ám ảnh của người mới chơi là mua lũa về thả vào bể, vài ngày sau nước vàng khè như nước chè. Đó là nhựa cây và chất Tanin. Cách xử lý triệt để:<br>\r\n    - <strong>Luộc lũa:</strong> Biện pháp hiệu quả nhất. Luộc nước sôi trong 30-60 phút kèm nhiều muối hột. Muối giúp đẩy nhựa cây ra nhanh hơn và sát khuẩn nấm mốc.<br>\r\n    - <strong>Ngâm oxy già:</strong> Nếu lũa quá to không luộc được, hãy ngâm trong thùng xốp với dung dịch Oxy già công nghiệp pha loãng trong 3 ngày.</p>\r\n\r\n    <h2>2. Các loại Lũa phổ biến</h2>\r\n    <ul>\r\n        <li><strong>Lũa Linh Sam:</strong> Vân thớ cực đẹp, cứng, chìm ngay lập tức. Thường dùng ghép bonsai.</li>\r\n        <li><strong>Lũa Hải Sơn Quỳ:</strong> Gai góc, hầm hố, thích hợp cho bể phong cách Rừng rậm (Jungle).</li>\r\n        <li><strong>Lũa Đỗ Quyên:</strong> Màu vàng sáng, nhiều nhánh uốn lượn mềm mại. Lưu ý loại này nhẹ, cần ngâm lâu mới chìm.</li>\r\n    </ul>\r\n\r\n    <h2>3. Bí kíp dán đá: Keo 502 + Giấy vệ sinh/Bụi cưa</h2>\r\n    <p>Làm sao các Master có thể xếp những tảng đá cheo leo mà không đổ? Họ không dùng keo silicon (khô lâu) mà dùng kỹ thuật \"Khớp nối bê tông\":<br>\r\n    <strong>Bước 1:</strong> Kẹp một miếng giấy ăn nhỏ (hoặc rắc bột đá/mùn cưa) vào giữa điểm tiếp xúc của 2 tảng đá/lũa.<br>\r\n    <strong>Bước 2:</strong> Nhỏ keo 502 (loại lỏng) thấm đẫm miếng giấy đó.<br>\r\n    <strong>Kết quả:</strong> Phản ứng hóa học sinh nhiệt sẽ làm hỗn hợp đông cứng ngay lập tức như xi măng, mối nối cực kỳ chắc chắn, chịu lực tốt hơn cả đá thật.</p>', 'http://localhost/uploads/ky_thuat_lua_da.jpg', 1, '2025-12-01 11:27:05'),
(26, 'Giải mã thông số nước: pH, TDS, gH, kH là gì và tại sao cá chết?', 'Cá chết không rõ nguyên nhân? Có thể bạn đang nuôi cá ưa kiềm trong môi trường axit. Hiểu về hóa học nước để làm chủ cuộc chơi.', '<p>Nước trong vắt không có nghĩa là nước sạch. Có những \"sát thủ vô hình\" trong nước mà mắt thường không thấy được, nhưng lại quyết định sự sống còn của sinh vật. Đó là các chỉ số hóa học.</p>\r\n\r\n    <h2>1. Độ pH (Potential of Hydrogen) - Độ chua/kiềm</h2>\r\n    <p>Thang đo từ 0-14, với 7 là trung tính. <br>\r\n    - <strong>pH < 7 (Axit):</strong> Phù hợp cho đa số cá nhiệt đới (Neon, Dĩa, Ông Tiên) và Tép màu, Tép ong.<br>\r\n    - <strong>pH > 7 (Kiềm):</strong> Phù hợp cho cá Bảy màu (Guppy), Cá Molly, Cá Ali, Tép Sulawesi.<br>\r\n    <strong>Nguy hiểm:</strong> Sốc pH. Khi thả cá mới mua vào bể, nếu pH chênh lệch quá 1.0 đơn vị, cá sẽ bị sốc, tuột nhớt và chết ngay lập tức. Hãy hòa nước từ từ (Drip Acclimation).</p>\r\n\r\n    <h2>2. TDS (Total Dissolved Solids) - Tổng chất rắn hòa tan</h2>\r\n    <p>Hiểu đơn giản là độ \"dơ\" hoặc độ \"đặc\" của nước. TDS bao gồm khoáng chất, muối, kim loại nặng, phân cá tan rã...<br>\r\n    - <strong>Tép cảnh:</strong> Cần TDS chuẩn (ví dụ Tép Ong cần TDS ~100-120) để lột vỏ. TDS quá cao vỏ cứng không lột được -> Chết.<br>\r\n    - <strong>Cách giảm TDS:</strong> Duy nhất là thay nước hoặc dùng nước lọc RO.</p>\r\n\r\n    <h2>3. Độ cứng gH (General Hardness) và kH (Carbonate Hardness)</h2>\r\n    <ul>\r\n        <li><strong>gH:</strong> Đo lượng Canxi và Magie. Cây thủy sinh và ốc cần gH đủ cao để không bị rữa lá, mòn vỏ.</li>\r\n        <li><strong>kH:</strong> Đo độ đệm của nước. kH càng cao thì pH càng ổn định, khó bị tụt giảm đột ngột (pH Crash).</li>\r\n    </ul>\r\n\r\n    <h2>Lời khuyên</h2>\r\n    <p>Đừng quá ám ảnh với con số chính xác tuyệt đối. Sự <strong>ỔN ĐỊNH</strong> quan trọng hơn. Cá có thể thích nghi với pH 7.5 dù sách nói cần 6.5, miễn là con số 7.5 đó được duy trì ổn định, không trồi sụt thất thường.</p>', 'http://localhost/uploads/thong_so_nuoc.jpg', 2, '2025-12-01 11:27:05'),
(34, 'Kỹ Thuật Thay Nước Bể Cá Chuẩn Chuyên Gia: Bí Quyết Giúp Cá Khỏe, Nước Trong Vắt', 'Thay nước không chỉ đơn giản là đổ nước cũ đi và thêm nước mới. Làm sai cách có thể khiến cá bị sốc, chết vi sinh và bùng phát rêu hại. Cùng MP Aquatic tìm hiểu quy trình chuẩn nhé!', '<article class=\"blog-detail\">\r\n    <p>Thay nước không chỉ đơn giản là đổ nước cũ đi và thêm nước mới. Làm sai cách có thể khiến cá bị sốc, chết vi sinh và bùng phát rêu hại. Cùng <strong>MP Aquatic</strong> tìm hiểu quy trình chuẩn nhé!</p>\r\n\r\n    <h3>1. Tại sao cần thay nước định kỳ?</h3>\r\n    <p>Nhiều người mới chơi (Newbie) thường mắc sai lầm: <em>\"Thấy nước trong thì không cần thay\"</em>. Đây là quan niệm sai lầm chết người.</p>\r\n    <p>Trong quá trình nuôi, chất thải của cá và thức ăn thừa tạo ra <strong>Nitrate (NO3-)</strong>. Dù hệ vi sinh có tốt đến đâu thì Nitrate vẫn tích tụ dần. Khi nồng độ này quá cao, cá sẽ bị stress, bỏ ăn, giảm đề kháng và rêu hại bùng phát.</p>\r\n    <p><strong>Mục tiêu:</strong> Thay nước là để loại bỏ độc tố tích tụ và bổ sung khoáng chất mới cho cá/tép.</p>\r\n\r\n    <h3>2. Chuẩn bị dụng cụ \"hành nghề\"</h3>\r\n    <ul>\r\n        <li><strong>Cây hút cặn (Siphon):</strong> Dụng cụ bắt buộc để hút phân cá dưới nền.</li>\r\n        <li><strong>Xô/Chậu:</strong> Để chứa nước thải.</li>\r\n        <li><strong>Dung dịch khử Clo:</strong> Nếu bạn dùng nước máy trực tiếp.</li>\r\n        <li><strong>Khăn lau:</strong> Để thấm nước vương vãi.</li>\r\n        <li><strong>Dụng cụ cọ kính:</strong> Cạo rêu bám thành bể.</li>\r\n    </ul>\r\n\r\n    <h3>3. Quy trình thay nước 5 bước chuẩn chỉ</h3>\r\n    \r\n    <h4>Bước 1: Tắt các thiết bị điện</h4>\r\n    <p>An toàn là trên hết. Hãy rút phích cắm của lọc, đèn và đặc biệt là <strong>sưởi</strong>. Nếu sưởi đang nóng mà mực nước hạ xuống thấp hơn thân sưởi, nó có thể bị nổ hoặc hỏng hóc.</p>\r\n\r\n    <h4>Bước 2: Vệ sinh mặt kính và cắt tỉa cây</h4>\r\n    <p>Hãy cọ sạch rêu bám kính và tỉa cây <em>trước khi</em> hút nước. Bụi bẩn và lá cây cắt ra sẽ trôi lơ lửng và được hút ra ngoài ở bước sau.</p>\r\n\r\n    <h4>Bước 3: Hút nước cũ (Kỹ thuật Siphon)</h4>\r\n    <p><strong>Nguyên tắc vàng:</strong> Chỉ thay <strong>20% - 30%</strong> lượng nước trong bể mỗi lần. Tuyệt đối không thay 100% nước.</p>\r\n    <p><strong>Cách hút:</strong> Cắm đầu hút xuống lớp nền (sỏi/cát) để hút sạch phân cá và thức ăn thừa lắng đọng.</p>\r\n    <p><em>Lưu ý:</em> Tuyệt đối <strong>KHÔNG vớt cá ra ngoài</strong> khi thay nước để tránh làm cá hoảng sợ.</p>\r\n\r\n    <h4>Bước 4: Xử lý nước mới</h4>\r\n    <p>Đây là bước quan trọng nhất. Nước máy thường chứa <strong>Clo</strong> gây chết cá.</p>\r\n    <ul>\r\n        <li>Dùng dung dịch khử Clo chuyên dụng (như Seachem Prime).</li>\r\n        <li>Cố gắng để nhiệt độ nước mới xấp xỉ nước trong bể để tránh sốc nhiệt.</li>\r\n    </ul>\r\n\r\n    <h4>Bước 5: Vào nước</h4>\r\n    <p>Đổ nước thật nhẹ nhàng. Hãy lót một chiếc đĩa nhỏ hoặc dùng chính bàn tay của bạn để hứng dòng nước chảy xuống, tránh làm xối nền.</p>\r\n    <p>Sau khi nước đầy, châm thêm <strong>Vi sinh tươi</strong>, bật lại lọc và sưởi.</p>\r\n\r\n    <h3>4. Những sai lầm thường gặp</h3>\r\n    <ul>\r\n        <li>Thay 100% nước gây sốc môi trường.</li>\r\n        <li>Giặt bông lọc quá sạch bằng nước máy làm chết vi sinh.</li>\r\n        <li>Quên tắt sưởi khi thay nước.</li>\r\n    </ul>\r\n\r\n    <div class=\"blog-cta\" style=\"background: #1a1a1a; padding: 20px; border-radius: 10px; margin-top: 30px; border: 1px solid #333;\">\r\n        <h4 style=\"color: #f1c40f; margin-top: 0;\">Lời kết</h4>\r\n        <p style=\"margin-bottom: 0;\">Thay nước định kỳ hàng tuần là liều thuốc bổ rẻ tiền nhất cho bể cá. Ghé ngay <strong>MP Aquatic</strong> để sắm đầy đủ bộ dụng cụ vệ sinh bể cá chính hãng nhé!</p>\r\n    </div>\r\n</article>', 'http://localhost/uploads/1764572976_ky_thuat_thay_nuoc.jpg', 1, '2025-12-01 14:09:36'),
(40, '1212', 'eqewq', 'qưeqwe', 'http://localhost/uploads/1778382760_1764584349_1764572914_betta_hm_12.jpg', 1, '2026-05-10 10:12:40'),
(41, '12312', '3123', 'qưeqwe', '1778385278589_temp_image_1778385276517.jpg', 1, '2026-05-10 14:39:03');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`, `userId`, `productId`, `quantity`, `created_at`, `updated_at`) VALUES
(46, 44, 1, 4, '2026-05-23 09:30:39', '2026-05-24 03:42:27');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(13, 'Cá cảnh', 'Các loại cá thủy sinh', '2025-11-22 17:00:00', NULL),
(14, 'Tép và Ốc', 'Tép cảnh và ốc dọn bể', '2025-11-22 17:00:00', NULL),
(15, 'Cây thủy sinh', 'Rêu, ráy, bucep, cắt cắm', '2025-11-30 03:09:08', NULL),
(16, 'Thiết bị bể cá', 'Đèn, lọc, sủi oxy, hẹn giờ', '2025-11-30 03:09:08', NULL),
(17, 'Vật tư & Trang trí', 'Phân nền, đá, lũa, thức ăn, vi sinh', '2025-11-30 03:09:08', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories_blog`
--

CREATE TABLE `categories_blog` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `categories_blog`
--

INSERT INTO `categories_blog` (`id`, `name`) VALUES
(1, 'Kỹ thuật Setup'),
(2, 'Chăm sóc bể'),
(3, 'Các loại cá & Tép');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `conversations`
--

CREATE TABLE `conversations` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `conversations`
--

INSERT INTO `conversations` (`id`, `user_id`, `admin_id`, `created_at`, `updated_at`) VALUES
(1, 44, 1, '2026-05-09 17:23:28', '2026-05-16 09:31:13');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `conversation_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `message` text DEFAULT NULL,
  `message_type` varchar(20) DEFAULT 'text',
  `is_read` tinyint(1) DEFAULT 0,
  `created_at` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `messages`
--

INSERT INTO `messages` (`id`, `conversation_id`, `sender_id`, `message`, `message_type`, `is_read`, `created_at`) VALUES
(1, 1, 44, 'hi', 'text', 1, '2026-05-09 17:23:28'),
(2, 1, 44, '1778347498220_temp_chat_image.jpg', 'image', 1, '2026-05-09 17:24:58'),
(3, 1, 44, 'hi\n\\', 'text', 1, '2026-05-16 09:26:39'),
(4, 1, 44, '1778923706268_temp_chat_image.jpg', 'image', 0, '2026-05-16 09:28:26'),
(5, 1, 44, '1778923717292_temp_chat_image.jpg', 'image', 0, '2026-05-16 09:28:37'),
(6, 1, 1, 'chat/1778923812_admin_6a083924f371e.jpg', 'image', 1, '2026-05-16 09:30:13'),
(7, 1, 1, 'chat/1778923873_admin_6a083961ba040.png', 'image', 1, '2026-05-16 09:31:13');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notifications`
--

CREATE TABLE `notifications` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT 0,
  `created_at` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `notifications`
--

INSERT INTO `notifications` (`id`, `userId`, `title`, `message`, `is_read`, `created_at`) VALUES
(1, 1, 'Đơn hàng mới', 'Bạn có đơn hàng mới cần xử lý', 0, '2026-05-09 09:51:52'),
(2, 1, 'Thanh toán thành công', 'Đơn hàng #123 đã thanh toán thành công', 1, '2026-05-09 09:51:52'),
(5, 1, 'Giao hàng', 'Đơn hàng của bạn đang được giao', 1, '2026-05-09 09:51:52'),
(6, 44, 'Đơn hàng mới', 'Bạn có đơn hàng mới cần xử lý', 0, '2026-05-09 09:52:24'),
(7, 44, 'Thanh toán thành công', 'Đơn hàng #123 đã thanh toán thành công', 1, '2026-05-09 09:52:24'),
(8, 44, 'Khuyến mãi', 'Giảm giá 20% cho tất cả sản phẩm hôm nay', 0, '2026-05-09 09:52:24'),
(9, 44, 'Cập nhật hệ thống', 'Hệ thống sẽ bảo trì lúc 23:00', 0, '2026-05-09 09:52:24'),
(10, 44, 'Giao hàng', 'Đơn hàng của bạn đang được giao', 1, '2026-05-09 09:52:24'),
(11, 44, 'gh', 'qưeqwe', 1, '2026-05-09 09:53:24'),
(12, 44, 'Đặt hàng thành công ?', 'Đơn hàng COD trị giá 45.000đ đã được ghi nhận. Chúng tôi sẽ sớm giao cá cho bạn!', 0, '2026-05-13 07:20:52'),
(13, 44, 'Thanh toán ZaloPay thành công ?', 'Đơn hàng trị giá 45.000đ đã được thanh toán. MP Aquatic sẽ đóng gói ngay lập tức!', 0, '2026-05-13 07:22:56'),
(14, 44, 'Đặt hàng thành công ?', 'Đơn hàng COD trị giá 85.000đ đã được ghi nhận. Chúng tôi sẽ sớm giao cá cho bạn!', 0, '2026-05-13 09:00:55'),
(15, 44, 'Đặt hàng thành công ?', 'Đơn hàng COD trị giá 45.000đ đã được ghi nhận. Chúng tôi sẽ sớm giao cá cho bạn!', 0, '2026-05-13 09:02:38'),
(16, 44, 'Đặt hàng thành công ?', 'Đơn hàng COD trị giá 45.000đ đã được ghi nhận. Chúng tôi sẽ sớm giao cá cho bạn!', 0, '2026-05-16 09:01:25'),
(17, 44, 'Thanh toán ZaloPay thành công ?', 'Đơn hàng trị giá 45.000đ đã được thanh toán. MQ Aquatic sẽ đóng gói ngay lập tức!', 0, '2026-05-16 09:19:51');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderdetails`
--

CREATE TABLE `orderdetails` (
  `id` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `categoryId` int(11) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `productQuantity` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orderdetails`
--

INSERT INTO `orderdetails` (`id`, `orderId`, `productId`, `productName`, `categoryId`, `price`, `productQuantity`, `status`, `created_at`, `updated_at`) VALUES
(57, 63, 2, 'Cá Bảy Màu Rồng Đỏ', 13, 25000, 1, NULL, '2025-12-10 17:00:00', NULL),
(65, 68, 2, 'Cá Bảy Màu Rồng Đỏ', 13, 25000, 1, NULL, '2025-12-11 17:00:00', NULL),
(66, 69, 2, 'Cá Bảy Màu Rồng Đỏ', 13, 25000, 3, NULL, '2026-01-04 17:00:00', NULL),
(67, 70, 33, 'Cá Thần Tiên Ai Cập', 13, 80000, 1, NULL, '2026-01-04 17:00:00', NULL),
(68, 70, 1, 'Cá Neon Vua', 13, 15000, 1, NULL, '2026-01-04 17:00:00', NULL),
(69, 71, 2, 'Cá Bảy Màu Rồng Đỏ', 13, 25000, 1, NULL, '2026-01-04 17:00:00', NULL),
(70, 77, 1, 'Cá Neon Vua', NULL, 15000, 3, NULL, NULL, NULL),
(71, 77, 33, 'Cá Thần Tiên Ai Cập', NULL, 80000, 5, NULL, NULL, NULL),
(72, 78, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(73, 78, 2, 'Cá Bảy Màu Rồng Đỏ', NULL, 25000, 4, NULL, NULL, NULL),
(74, 79, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(75, 80, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(76, 80, 2, 'Cá Bảy Màu Rồng Đỏ', NULL, 25000, 1, NULL, NULL, NULL),
(77, 81, 1, 'Cá Neon Vua', NULL, 15000, 4, NULL, NULL, NULL),
(78, 81, 2, 'Cá Bảy Màu Rồng Đỏ', NULL, 25000, 1, NULL, NULL, NULL),
(79, 82, 1, 'Cá Neon Vua', NULL, 15000, 2, NULL, NULL, NULL),
(80, 83, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(81, 84, 1, 'Cá Neon Vua', NULL, 15000, 2, NULL, NULL, NULL),
(82, 85, 1, 'Cá Neon Vua', NULL, 15000, 2, NULL, NULL, NULL),
(83, 86, 1, 'Cá Neon Vua', NULL, 15000, 2, NULL, NULL, NULL),
(84, 87, 1, 'Cá Neon Vua', NULL, 15000, 2, NULL, NULL, NULL),
(85, 88, 1, 'Cá Neon Vua', NULL, 15000, 3, NULL, NULL, NULL),
(86, 89, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(87, 90, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(88, 91, 2, 'Cá Bảy Màu Rồng Đỏ', NULL, 25000, 2, NULL, NULL, NULL),
(89, 92, 57, 'Văn Phúc3', 13, 21321321, 8, NULL, '2026-05-09 17:00:00', NULL),
(90, 92, 56, 'Văn Phúc3', 13, 21321321, 1, NULL, '2026-05-09 17:00:00', NULL),
(91, 93, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(92, 94, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(93, 95, 1, 'Cá Neon Vua', NULL, 15000, 2, NULL, NULL, NULL),
(94, 95, 2, 'Cá Bảy Màu Rồng Đỏ', NULL, 25000, 1, NULL, NULL, NULL),
(95, 96, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(96, 97, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL),
(97, 98, 1, 'Cá Neon Vua', NULL, 15000, 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `addressid` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `deliveryStatus` varchar(13) NOT NULL DEFAULT 'pending',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `userId`, `addressid`, `total`, `status`, `deliveryStatus`, `created_at`, `updated_at`) VALUES
(63, 1, 0, 25000, 'pending', 'delivered', '2025-12-10 23:54:51', '2025-12-11 05:55:08'),
(68, 40, 0, 25000, 'paid', 'delivered', '2025-12-12 00:52:49', '2025-12-12 06:54:00'),
(69, 44, 14, 75000, 'pending', 'shipping', '2026-01-05 04:08:33', NULL),
(70, 44, 14, 95000, 'pending', 'pending', '2026-01-05 06:25:25', NULL),
(71, 44, 14, 25000, 'pending', 'delivered', '2026-01-05 06:32:21', NULL),
(77, 44, 14, 475000, 'pending', 'pending', '2026-05-08 15:27:31', NULL),
(78, 44, 20, 145000, 'pending', 'pending', '2026-05-08 15:37:14', NULL),
(79, 44, 14, 45000, 'pending', 'pending', '2026-05-08 15:39:54', NULL),
(80, 44, 20, 70000, 'pending', 'pending', '2026-05-09 01:53:23', NULL),
(81, 44, 15, 115000, 'pending', 'pending', '2026-05-09 01:57:16', NULL),
(82, 44, 14, 60000, 'pending', 'pending', '2026-05-09 02:04:22', NULL),
(83, 44, 15, 45000, 'pending', 'pending', '2026-05-09 02:56:22', NULL),
(84, 44, 15, 60000, 'pending', 'pending', '2026-05-09 03:17:36', NULL),
(85, 44, 15, 60000, 'pending', 'pending', '2026-05-09 07:51:09', NULL),
(86, 44, 15, 60000, 'pending', 'pending', '2026-05-09 07:56:40', NULL),
(87, 44, 14, 60000, 'pending', 'pending', '2026-05-09 08:10:01', NULL),
(88, 44, 15, 75000, 'paid', 'shipping', '2026-05-09 08:26:01', NULL),
(89, 44, 20, 45000, 'paid', 'shipping', '2026-05-09 08:27:28', '2026-05-09 11:17:34'),
(90, 44, 20, 45000, 'pending', 'pending', '2026-05-09 16:52:32', NULL),
(91, 44, 20, 80000, 'paid', 'pending', '2026-05-09 16:53:30', NULL),
(92, 52, 20, 191891889, 'pending', 'delivered', '2026-05-10 03:43:40', '2026-05-10 08:44:30'),
(93, 44, 14, 45000, 'pending', 'pending', '2026-05-13 07:20:52', NULL),
(94, 44, 15, 45000, 'paid', 'pending', '2026-05-13 07:22:56', NULL),
(95, 44, 14, 85000, 'pending', 'delivered', '2026-05-13 09:00:55', NULL),
(96, 44, 14, 45000, 'pending', 'delivered', '2026-05-13 09:02:38', NULL),
(97, 44, 14, 45000, 'pending', 'delivered', '2026-05-16 09:01:25', NULL),
(98, 44, 14, 45000, 'paid', 'pending', '2026-05-16 09:19:51', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`, `quantity`, `categoryId`, `created_at`, `updated_at`) VALUES
(1, 'Cá Neon Vua', '<p>Cá bơi theo đàn cực đẹp</p>', 15000, 25, 13, '2025-11-25 10:21:52', NULL),
(2, 'Cá Bảy Màu Rồng Đỏ', '<p>Dòng cá khỏe, dễ sinh sản</p>', 25000, 4, 13, '2025-11-25 10:21:52', NULL),
(3, 'Tép Yamato', '<p>Chuyên gia dọn rêu hại</p>', 35000, 43, 14, '2025-11-25 10:21:52', NULL),
(4, 'Ốc Nerita', '<p>Dọn bể kính sạch bong</p>', 10000, 110, 14, '2025-11-25 10:21:52', NULL),
(5, 'Cây Ráy Nana', '<p>Cây thủy sinh tiền cảnh</p>', 45000, 30, 15, '2025-11-25 10:21:52', NULL),
(6, 'Đèn Chihiros WRGB', '<p>Đèn cao cấp cho bể 60cm</p>', 1200000, 9, 16, '2025-11-25 10:21:52', NULL),
(7, 'Lọc Thùng Atman', '<p>Lọc ngoại êm ái, hiệu quả</p>', 850000, 14, 16, '2025-11-25 10:21:52', NULL),
(8, 'Cá Sóc Đầu Đỏ', '<p>Bơi theo đàn rất chặt</p>', 12000, 200, 13, '2025-11-25 10:21:52', NULL),
(9, 'Tép RC (Red Cherry)', '<p>Tép màu đỏ nổi bật</p>', 5000, 499, 14, '2025-11-25 10:21:52', NULL),
(10, 'Cá Chuột Panda', '<p>Dọn thức ăn thừa đáy bể</p>', 20000, 40, 13, '2025-11-25 10:21:52', NULL),
(11, 'Phân Nền Gex Xanh', '<p>Phân nền chuyên tép</p>', 180000, 21, 17, '2025-11-25 10:21:52', NULL),
(12, 'Cá Betta Halfmoon', '<p>Vây đuôi xòe 180 độ</p>', 150000, 19, 13, '2025-11-25 10:21:52', NULL),
(13, 'Rêu Java Moss', '<p>Rêu dễ trồng, không cần CO2</p>', 30000, 60, 15, '2025-11-25 10:21:52', NULL),
(14, 'Cá Trâm', '<p>Cá siêu nhỏ cho bể nano</p>', 8000, 300, 13, '2025-11-25 10:21:52', NULL),
(15, 'Máy Sủi Oxy 2 Vòi', '<p>Siêu êm, tiết kiệm điện</p>', 65000, 45, 16, '2025-11-25 10:21:52', NULL),
(16, 'Vật Liệu Lọc Matrix', '<p>Đá lọc vi sinh cao cấp</p>', 380000, 30, 17, '2025-11-25 10:21:52', NULL),
(17, 'Cá Dĩa Xanh', '<p>Vua của các loài cá cảnh</p>', 450000, 5, 13, '2025-11-25 10:21:52', NULL),
(18, 'Tép Mũi Đỏ', '<p>Diệt rêu tóc hiệu quả</p>', 15000, 90, 14, '2025-11-25 10:21:52', NULL),
(19, 'Cây Bucep Ghost', '<p>Dòng Bucep lá nước đẹp</p>', 250000, 15, 15, '2025-11-25 10:21:52', NULL),
(20, 'Hẹn Giờ Cơ', '<p>Hẹn giờ bật tắt đèn</p>', 80000, 50, 16, '2025-11-25 10:21:52', NULL),
(21, 'Cá Hồng Nhung', '<p>Màu đỏ hồng đẹp mắt</p>', 10000, 138, 13, '2025-11-25 10:21:52', NULL),
(22, 'Lũa Linh Sam', '<p>Lũa chìm, dáng bon sai</p>', 120000, 30, 17, '2025-11-25 10:21:52', NULL),
(23, 'Đá Da Voi', '<p>Setup layout núi đá</p>', 35000, 100, 17, '2025-11-25 10:21:52', NULL),
(24, 'Thức Ăn Cá Cám Thái', '<p>Hạt nhỏ, thơm, lâu tan</p>', 25000, 200, 17, '2025-11-25 10:21:52', NULL),
(25, 'Vi Sinh Extra Bio', '<p>Làm trong nước nhanh chóng</p>', 90000, 60, 17, '2025-11-25 10:21:52', NULL),
(26, 'Cá Phượng Hoàng Lam', '<p>Màu sắc sặc sỡ</p>', 60000, 25, 14, '2025-11-25 10:21:52', NULL),
(27, 'Cây Trân Châu Ngọc Trai', '<p>Thảm xanh mướt bể</p>', 50000, 40, 13, '2025-11-25 10:21:52', NULL),
(28, 'CO2 Lỏng Seachem', '<p>Bổ sung Carbon cho cây</p>', 220000, 20, 14, '2025-11-25 10:21:52', NULL),
(29, 'Bình CO2 Nhôm 1L', '<p>An toàn, thẩm mỹ</p>', 650000, 10, 13, '2025-11-25 10:21:52', NULL),
(30, 'Cá Otto', '<p>Chăm chỉ lau kính, lá cây</p>', 40000, 55, 14, '2025-11-25 10:21:52', NULL),
(31, 'Sưởi Inox 100W', '<p>Giữ ấm mùa đông</p>', 95000, 35, 13, '2025-11-25 10:21:52', NULL),
(32, 'Cát Nắng Vàng', '<p>Cát trải nền tự nhiên</p>', 15000, 100, 14, '2025-11-25 10:21:52', NULL),
(33, 'Cá Thần Tiên Ai Cập', '<p>Dáng bơi khoan thai</p>', 80000, 14, 13, '2025-11-25 10:21:52', NULL),
(49, 'Cá ', '<p>SSSS</p>', 10000, 21, 14, '2026-01-05 07:16:37', '2026-01-05 07:23:38'),
(52, 'sản phẩm 1', '', 211, 100, 15, '2026-01-05 09:37:18', NULL),
(55, 'Văn Phúc2', '', 21321321, 100, 13, '2026-05-10 01:47:24', NULL),
(56, 'Văn Phúc3', '', 21321321, 99, 13, '2026-05-10 01:49:26', NULL),
(57, 'Văn Phúc3', '', 21321321, 92, 13, '2026-05-10 02:08:38', '2026-05-10 02:19:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_images`
--

CREATE TABLE `product_images` (
  `id` int(11) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_images`
--

INSERT INTO `product_images` (`id`, `image_path`, `product_id`) VALUES
(1, 'neon_vua_01.jpg', 1),
(2, 'guppy_red_02.jpg', 2),
(3, 'yamato_03.jpg', 3),
(4, 'nerita_04.jpg', 4),
(5, 'nana_05.jpg', 5),
(6, 'http://localhost/uploads/den_led_06.jpg', 6),
(7, 'http://localhost/uploads/loc_atman_07.jpg', 7),
(8, 'http://localhost/uploads/soc_dau_do_08.jpg', 8),
(9, 'http://localhost/uploads/tep_rc_09.jpg', 9),
(10, 'http://localhost/uploads/chuot_panda_10.jpg', 10),
(11, 'http://localhost/uploads/gex_xanh_11.jpg', 11),
(12, 'http://localhost/uploads/betta_hm_12.jpg', 12),
(13, 'http://localhost/uploads/java_moss_13.jpg', 13),
(14, 'http://localhost/uploads/ca_tram_14.jpg', 14),
(15, 'http://localhost/uploads/sui_oxy_15.jpg', 15),
(16, 'http://localhost/uploads/matrix_16.jpg', 16),
(17, 'http://localhost/uploads/ca_dia_17.jpg', 17),
(18, 'http://localhost/uploads/tep_mui_do_18.jpg', 18),
(19, 'http://localhost/uploads/bucep_19.jpg', 19),
(20, 'http://localhost/uploads/timer_20.jpg', 20),
(21, 'http://localhost/uploads/hong_nhung_21.jpg', 21),
(22, 'http://localhost/uploads/lua_linh_sam_22.jpg', 22),
(23, 'http://localhost/uploads/da_da_voi_23.jpg', 23),
(24, 'http://localhost/uploads/cam_thai_24.jpg', 24),
(25, 'http://localhost/uploads/extra_bio_25.jpg', 25),
(26, 'http://localhost/uploads/phuong_hoang_26.jpg', 26),
(27, 'http://localhost/uploads/tcnt_27.jpg', 27),
(28, 'http://localhost/uploads/co2_seachem_28.jpg', 28),
(29, 'http://localhost/uploads/binh_co2_29.jpg', 29),
(30, 'http://localhost/uploads/ca_otto_30.jpg', 30),
(31, 'http://localhost/uploads/suoi_inox_31.jpg', 31),
(32, 'http://localhost/uploads/cat_nang_32.jpg', 32),
(33, 'thantien.jpg', 33),
(38, '1778395644_vfuc.jpg', 55),
(39, '1778395644_timer_20.jpg', 55),
(40, 'bucep_19.jpg', 56),
(41, '1766538222_imager1989700-16850064207311454081042.jpg', 56),
(48, 'java_moss_13.jpg', 57),
(49, '1764572914_betta_hm_12.jpg', 57),
(50, '1764572976_ky_thuat_thay_nuoc.jpg', 57),
(51, '1764584349_1764572914_betta_hm_12.jpg', 57);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `provinces`
--

CREATE TABLE `provinces` (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `provinces`
--

INSERT INTO `provinces` (`id`, `name`) VALUES
(1, 'Thành phố Hà Nội'),
(2, 'Thành phố Đà Nẵng'),
(3, 'Tuyên Quang'),
(4, 'Lạng Sơn'),
(5, 'Phú Thọ'),
(6, 'Hưng Yên'),
(7, 'Ninh Bình'),
(8, 'Nghệ An'),
(9, 'Tỉnh Hưng Yên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `orderid` int(11) NOT NULL,
  `rating` int(11) NOT NULL DEFAULT 5,
  `comment` text DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `reviews`
--

INSERT INTO `reviews` (`id`, `user_id`, `product_id`, `orderid`, `rating`, `comment`, `image`, `created_at`) VALUES
(21, 44, 2, 71, 5, 'oke', '1778662207343_temp_review_image_1778662202470.jpg', '2026-05-13 08:50:07'),
(22, 44, 1, 95, 5, 'fdsfds', NULL, '2026-05-13 09:02:00'),
(23, 44, 2, 95, 5, 'dsf', NULL, '2026-05-13 09:02:06'),
(24, 44, 1, 96, 3, 'fdsfd', NULL, '2026-05-13 09:02:59'),
(25, 44, 1, 97, 5, 'cas dp', '1778922191654_temp_review_image_1778922184588.jpg', '2026-05-16 09:03:11');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'admin', '2025-11-17 07:11:58', NULL),
(2, 'user', '2025-11-17 07:11:58', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `numberPhone` varchar(10) NOT NULL,
  `roleid` int(11) NOT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `email`, `password`, `numberPhone`, `roleid`, `create_at`, `updated_at`, `avatar`) VALUES
(1, 'Hồ Đức Mạnh', 'manhnovar', 'supermoffcial@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0388730432', 1, '2025-11-19 17:00:00', '2025-12-03 01:10:53', '1764724253_betta_hm_12.jpg'),
(40, '', 'supermoffcial@gmail.com', 'supermoffcial1@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0989123456', 2, '2025-12-11 17:00:00', NULL, NULL),
(41, 'Khách Hàng VIP', 'khachhang_thuysinh@gmail.com', 'khachhang_thuysinh@gmail.com', '$2a$10$qvbhoCfsrXaHQzAVvUY2hOwbeL2Q08FhPgLzYWeC0spKbVxot2NFu', '0901234567', 2, '2026-05-04 08:56:28', NULL, NULL),
(43, 'Nguyễn Văn A', 'asdasdas', 'asdasdas', '$2a$10$YPcjnILZv/EhnJTsAxG4TuLmQPZrm2jKWdogleFvzTuHOWHVsNaDC', '', 2, '2026-05-04 13:11:40', NULL, NULL),
(44, 'Nguyễn Văn D', 'abc123', 'abc123@gmail.com', '$2a$10$PY4//RDTINbQ.Uc6nvAn/u0J4Yp1wwcd.uC13tDNWHR67VROGRdJm', '0325863501', 2, '2026-05-04 23:37:59', NULL, '1778088091340_temp_image_1778088087704.jpg'),
(52, 'Thành viên mớii', 'abc1234@gmail.com', 'abc1234@gmail.com', '$2y$10$3Ry.6L4rashG6cEo9NSh1uQY61jsnEc.q0dRyIGT7gGbAry7XhWHK', '0232131233', 1, '2026-05-10 03:17:42', '2026-05-10 08:10:12', '1764572914_betta_hm_12.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wards`
--

CREATE TABLE `wards` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `proviceId` int(11) NOT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `wards`
--

INSERT INTO `wards` (`id`, `name`, `proviceId`, `create_at`, `updated_at`) VALUES
(1, 'Phường Long Biên', 1, NULL, NULL),
(2, 'Xã Hòa Tiến', 1, NULL, NULL),
(3, 'Xã Thạnh Bình', 1, NULL, NULL),
(4, 'Xã Khâu Vai', 1, NULL, NULL),
(5, 'Xã Quý Hòa', 1, NULL, NULL),
(6, 'Xã Liên Sơn', 1, NULL, NULL),
(7, 'Xã Đức Phú', 1, NULL, NULL),
(8, 'Phường Ô Chợ Dừa', 1, NULL, NULL),
(9, 'Phường Yên Hòa', 1, NULL, NULL),
(10, 'Phường Cầu Giấy', 1, NULL, NULL),
(11, 'Xã Việt Yên', 1, NULL, NULL),
(12, 'Phường Liêm Tuyền', 1, NULL, NULL),
(13, 'Xã Hòa Vang', 1, NULL, NULL),
(14, 'Xã Châu Bình', 1, NULL, NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `proviceId` (`proviceId`),
  ADD KEY `userId` (`userId`),
  ADD KEY `wardId` (`wardId`);

--
-- Chỉ mục cho bảng `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_blogs_categories` (`category_id`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_user_product` (`userId`,`productId`),
  ADD KEY `fk_cart_product` (`productId`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `categories_blog`
--
ALTER TABLE `categories_blog`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `conversations`
--
ALTER TABLE `conversations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`,`admin_id`),
  ADD KEY `admin_id` (`admin_id`);

--
-- Chỉ mục cho bảng `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `idx_conversation` (`conversation_id`),
  ADD KEY `idx_created_at` (`created_at`);

--
-- Chỉ mục cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_notifications_user` (`userId`);

--
-- Chỉ mục cho bảng `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orderId` (`orderId`),
  ADD KEY `productId` (`productId`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userId` (`userId`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoryId` (`categoryId`);

--
-- Chỉ mục cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `productId` (`product_id`);

--
-- Chỉ mục cho bảng `provinces`
--
ALTER TABLE `provinces`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `order_id` (`orderid`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_username` (`username`),
  ADD KEY `Users_Roles` (`roleid`);

--
-- Chỉ mục cho bảng `wards`
--
ALTER TABLE `wards`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_wards_provinces` (`proviceId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `blogs`
--
ALTER TABLE `blogs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT cho bảng `categories_blog`
--
ALTER TABLE `categories_blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `conversations`
--
ALTER TABLE `conversations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `orderdetails`
--
ALTER TABLE `orderdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT cho bảng `product_images`
--
ALTER TABLE `product_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT cho bảng `provinces`
--
ALTER TABLE `provinces`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT cho bảng `wards`
--
ALTER TABLE `wards`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`proviceId`) REFERENCES `provinces` (`id`),
  ADD CONSTRAINT `address_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `address_ibfk_3` FOREIGN KEY (`wardId`) REFERENCES `wards` (`id`);

--
-- Các ràng buộc cho bảng `blogs`
--
ALTER TABLE `blogs`
  ADD CONSTRAINT `fk_blogs_categories` FOREIGN KEY (`category_id`) REFERENCES `categories_blog` (`id`);

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_product` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `conversations`
--
ALTER TABLE `conversations`
  ADD CONSTRAINT `conversations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `conversations_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`conversation_id`) REFERENCES `conversations` (`id`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `fk_notifications_user` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_users` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`);

--
-- Các ràng buộc cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `fk_product_images` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reviews_ibfk_3` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `Users_Roles` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`);

--
-- Các ràng buộc cho bảng `wards`
--
ALTER TABLE `wards`
  ADD CONSTRAINT `fk_wards_provinces` FOREIGN KEY (`proviceId`) REFERENCES `provinces` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
