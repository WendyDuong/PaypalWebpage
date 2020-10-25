package com.example.android.demoapp.database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android.demoapp.AppExecutors;
import com.example.android.demoapp.R;


@Database(entities = {SanPhamEntry.class, GioHangEntry.class, YeuThichEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "demoapp";
    private static AppDatabase sInstance;


    public synchronized static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = buildDatabase(context);
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                AppDatabase.DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(0, "Táo Pink Lady", 150000,
                                        R.drawable.taopinklady, "500g", "Táo Pink Lady New Zealand từ khi ra đời đã nhanh chóng chiếm được trái tim của người tiêu dùng, với hương vị tươi ngon, vỏ màu hồng đẹp tự nhiên, cùng nhiều thành phần dinh dưỡng bên trong, rất tốt cho sức khỏe.",
                                        "Hit", "New Zealand"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 0, "Nho xanh ", 80000, R.drawable.nhoxanh, "500g", "Hương vị thơm ngon, giòn ngọt tự nhiên. Cung cấp nhiều vitamin và khoáng chất tốt cho cơ thể",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 0, "Socola Toffifee", 40000, R.drawable.socolatofiiee, "125g", "Socola Toffifee Kẹo bao phủ bên ngoài lớp caramen dẻo ngọt, lớp tiếp theo là socola và ở giữa là hạt hạnh nhân. Thưởng thức kẹo Toffifee đem đến cảm giác rất thú vị bởi sự kết hợp giữa vị ngọt của sữa, trứng gà, cafe, 1 ít bột vani cùng sữa tươi, dư chút vị đắng của socola và giòn bùi ẩn sâu bên trong cùng chiếc kẹo",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 1, "Mercedes C200", 1300000000, R.drawable.merc200, "Mau Trang", "Xe được nhớ đến bởi vẻ ngoài hài hòa giữa nét thể thao và lịch lãm, trang bị tiện nghi sang trọng, cao cấp đi kèm một mức giá vừa phải cho một sản phẩm tới từ thương hiệu lừng danh Mercedes Benz.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 2, "Bánh Sisi Mix Brand", 50000, R.drawable.banhsisi, "220g","Bánh kẹo Lumar Sisi Mix được làm từ Socola hảo hạn",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 2, "Táo Mỹ", 180000, R.drawable.taomy, "300g", "Táo Mỹ mang vị ngọt tự nhiên, độ giòn hoàn hảo", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 2, "Socola Ferrero", 80000, R.drawable.socolaferrero, "200g", "Ferrero Rocher được sản xuất tại Ý từ năm 1982 và nổi tiếng trên toàn thế giới về vị ngon & độ tinh xảo, từ chất lượng sản phẩm đến sự độc đáo về hình thức.",
                                        "Hit", "Đức"));

                                Log.d(LOG_TAG, "insert data in database");
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 2, "Bánh cá Fancy", 45000, R.drawable.banhfancy, "250g", "Bánh cá Fancy vàng ươm, thơm phức, ngon tuyệt cú mèo.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 2, "Socola Milka", 40000, R.drawable.socolamilka, "90g", "Với quy trình sản xuất hiện đại, nguyên liệu được chọn lựa kỹ lưỡng tại 8 quốc gia khác nhau, những thanh socola Milka mang trong mình thứ hương vị thơm ngon, tinh tế và hoàn toàn khác biệt.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 2, "Bánh Quy Leibniz", 20000, R.drawable.banhleibniz, "200g", "Bánh quy Leibniz được sản xuất trên dây chuyền công nghệ hiện đại, nhập khẩu trực tiếp từ Đức, hương vị thơm ngon giúp bổ sung năng lượng hàng ngày.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(2, "Socola Merci", 30000, R.drawable.socolamerci, "250g", "Kẹo socola Merci được làm từ bột ca cao hảo hạng nhất, nguyên liệu tốt nhất. Hương vị đậm nét đặc trưng, thiết kế bao bì bắt mắt, hấp dẫn. Merci có hương vị ngọt ngào pha lẫn chút đắng nhẹ nơi đầu lưỡi, chút béo ngậy của sữa và hương cacao, tất cả tạo nên một hương vị đặc biệt đầy tinh tế.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 2, "Socola Ritter Sport", 15000, R.drawable.socolarittersport, "200g", "Ritter Sport là sản phẩm nổi tiếng của Đức và được xuất khẩu đến hơn 90 nước trên thế giới. Hương vị không thể nhầm lẫn!", "Hit", "Đức"));



                               //Hãng 3: Rosmann
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 3, "Nước Hoa Calvin Klein", 700000, R.drawable.nuochoack, "50ml", "Tuy sở hữu thiết kế đơn giản nhưng hương thơm ngọt ngào mà những sản phẩm này tạo ra luôn được đánh giá cao. Calvin Klein đã nhận nhiều giải thưởng danh giá dành cho nước hoa thông qua các sáng tạo của mình.", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(3, "Nước hoa DG", 1500000, R.drawable.nuochoadg, "100ml", "Sự tinh tế hòa quyện giữa nét nam tính cuốn hút và đầy lịch lãm, The One for Men Eau de Parfum Intense mang đến hương thơm nồng nàn đột phá cho bộ sưu tập Dolce&Gabbana The One for Men", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 3, "Nước Hoa HUGO BOSS", 1000000, R.drawable.nuochoahugo, "75ml", "Boss The Scent Men Là dòng nước hoa mang xu hướng nhẹ nhàng, bình yên nhưng quyết đoán mà nhãn hiệu nước hoa Hugo đã tạo nên. Đây là một hương thơm quen thuộc nhưng vẫn giữ được nét riêng biệt mà ít có dòng nước hoa nào làm được vậy.", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 3, "Nước hoa Tom Ford", 2000000, R.drawable.nuochoatomford, "50ml", "Oud Wood-gỗ trầm hương không những trở thành một trong những tác phẩm cổ điển của riêng thương hiệu Tom Ford, mà có một bộ sưu tập mới với tên gọi “Oud Collection” đã được ra đời với các mùi hương gỗ trầm hương, gỗ đàn hương và hoắc hương giữ vai trò chủ đạo", "Hit", "Đức"));



                                //Hãng 4: Apotheke
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Xi-rô trị ho không chứa cồn", 443190 , R.drawable.a1, "200 ml", "Xi-rô ho không chứa cồn từ apo-chiết khấu giúp làm dịu tự nhiên sự kích ứng của màng nhầy họng và miệng cũng như ho khan hoặc ho khó chịu liên quan." +
                                        "Thuốc là thuốc nam gia truyền được đăng ký khu vực áp dụng chỉ vì công dụng lâu dài.", "apo-discounter", "Đức" ));
                                    sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"ACC akut 600mg thuốc ho", 947070, R.drawable.a2, "40 viên", "Thuốc tẩy ho cấp tính ACC 600mg (kích thước gói: 40 chiếc) - là một loại thuốc tẩy ho." +
                                        "" +
                                        "Cảm lạnh thường đi kèm với ho dai, có đờm và chảy nước mũi (nhiễm trùng xoang). Tất nhiên, nếu bạn đã bị cúm thật (nhiễm trùng giống cúm), thì cơn sốt cũng không còn xa nữa. Bạn nằm trên giường mà bủn rủn chân tay, tứ chi duỗi ra, tốt nhất là trốn vào phòng tối uống trà. Tất cả và cuối cùng những gì bạn cần là nghỉ ngơi để thư giãn, nhưng điều đó chỉ có thể thực hiện được nếu bạn không thường xuyên tỉnh táo vì cơn ho." +
                                        "" +
                                        "Vậy làm cách nào để hết ho có đờm?" +
                                        "Chất nhầy bị mắc kẹt làm tắc các lông mao của màng nhầy phế quản và ngăn không cho nó vận chuyển ra bên ngoài. Thuốc giảm ho cấp tính ACC 600mg (kích thước gói: 40 viên) giúp bạn làm lỏng chất nhầy và giải phóng các lông mao bị tắc của niêm mạc đường thở. Do đó, việc loại bỏ được thực hiện và cuối cùng bạn đã được giải phóng khỏi các triệu chứng đau đớn của cơn ho có đờm." +
                                        "Thuốc giảm ho cấp tính ACC 600mg (kích thước gói: 40 chiếc) rất dễ sử dụng và có hương vị dâu đen nhẹ nhàng dễ chịu.", "ACC", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Gelomyrtol", 1323960, R.drawable.a3, "60 viên", "Đầu óc căng thẳng? Bịt mũi lại Để ho? Viên nang mềm kháng dịch vị Gelomyrtol forte (gói 60 viên) là thuốc long đờm thảo dược trị ho, sổ mũi và nhức đầu. Nó làm lỏng chất nhờn, giảm viêm và thúc đẩy quá trình chữa bệnh. Kết quả là bạn có thể thở thoải mái trở lại, ít bị ho hơn và nhanh khỏe hơn." +
                                        "" +
                                        "Lời khuyên: Uống viên nang mềm kháng dạ dày Gelomyrtol forte (kích thước gói: 60 viên) khi có dấu hiệu đầu tiên của cảm lạnh. Bằng cách này, bạn có thể ngăn chặn tình trạng trầm trọng hơn và rút ngắn thời gian của bệnh." +
                                        "" +
                                        "Viên nang mềm kháng dạ dày Gelomyrtol forte (kích thước gói: 60 viên) làm thông thoáng đường thở ngay từ viên nang đầu tiên.", "GeloMyrtol", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Thuốc ho Dextromethorphan", 287640, R.drawable.a4, "10 viên", "Thuốc giảm ho ratiopharm dextromethorphan (gói: 10 miếng) là thuốc giảm ho cho các trường hợp ho có đờm.", "ratiopharm", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Wick Daymed Hartkapseln", 543660, R.drawable.a5, "20 viên", "Viên nang cứng Wick Daymed (gói 20 viên) - Dành cho những người muốn duy trì hoạt động dù đang bị cảm." +
                                        "" +
                                        "Đối với những người muốn duy trì hoạt động mặc dù bị nhiễm trùng giống cúm" +
                                        "Là một phương pháp điều trị cảm lạnh, viên nang cứng WICK DayMed là một giải pháp lý tưởng luôn có sẵn để giảm bớt các triệu chứng cảm lạnh thông thường nhất. Viên nang cứng thực tế có chứa 3 hoạt chất - paracetamol, phenylpropanolamine và dextromethorphan - giúp giảm bớt 5 triệu chứng cảm lạnh: sốt thấp, nhức đầu và đau người, đau họng, sổ mũi và ho khan. WICK DayMed không chứa bất kỳ thành phần hoạt tính nào khiến bạn mệt mỏi, vì vậy không có gì cản đường cho một ngày năng động bất chấp cảm lạnh.ii iii" +
                                        "" +
                                        "Để có một giấc ngủ yên bình và thư thái, Wick Medinait là nơi lý tưởng cho ban đêm." +
                                        "" +
                                        "Người tiêu dùng cũng tin tưởng các sản phẩm của WICK, và năm 2018 thương hiệu này đã nhận được Giải thưởng Pegasus từ tạp chí Reader's Digest với tư cách là Thương hiệu đáng tin cậy nhất trong lĩnh vực lạnh lần thứ 18 liên tiếp và năm nay đã chiếm ưu thế so với tổng số 173 thương hiệu được nêu tên." +
                                        "" +
                                        "1.i chẳng hạn như thuốc kháng histamine an thần của thế hệ đầu tiên" +
                                        "2.ii Tải xuống văn bản bắt buộc ‘! qua liên kết h" +
                                        "3.iii ttp: //cdn.genesis.pgsitecore.com/de-de/-/media/Vicks_DE/Images/Products/PDF%20files/DayMed%20Erkaeltungskapseln%20Leaflet_Aug%2012.pdf? La = de-DE & v = 1 -201609191418" +
                                        "4.iv bình chọn Thương hiệu đáng tin cậy nhất trong lĩnh vực cảm lạnh do độc giả của tạp chí Reader’s Digest “" +
                                        "5.v Nghiên cứu về Procter & Gamble, Tệp # RPS1147 / 44. Chứng minh Độ dài của VapoRub trên 8 giờ. 2011.", "WICK","Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Meditonsin Tropfen", 1044990, R.drawable.a6, "2X 50g", "Thuốc nhỏ giọt Meditonsin (kích thước gói: 2X50 g) là một loại thuốc vi lượng đồng căn đã đăng ký. Các lĩnh vực ứng dụng có nguồn gốc từ hình ảnh thuốc vi lượng đồng căn. Chúng bao gồm: Viêm họng cấp, mũi họng. Theo các nguyên tắc của vi lượng đồng căn, mỗi phương pháp điều trị được thực hiện với một loại thuốc được điều chỉnh riêng cho bệnh nhân và bệnh cảnh lâm sàng của họ. Các biện pháp đơn vi lượng đồng căn khác nhau chắc chắn có thể được sử dụng cho các bệnh khác nhau." +
                                        "" +
                                        "Thuốc cảm của tôi" +
                                        "Thuốc đã được chứng minh hàng triệu lần trong việc điều trị cảm lạnh và các bệnh nhiễm trùng như cúm trong hơn 60 năm." +
                                        "" +
                                        "Thuốc chữa cảm lạnh Meditonsin chứa sự kết hợp thông minh của ba thành phần bổ sung, hoạt chất tự nhiên (tri-complex) chống lại cảm lạnh." +
                                        "" +
                                        "Mẹo: Phức hợp Tri-Complex duy nhất cũng có sẵn ở dạng rắn như các hạt cầu." +
                                        "Dấu hiệu đầu tiên của cảm lạnh? Meditonsin® giúp ở đây" +
                                        "" +
                                        "Meditonsin® truyền các xung động cụ thể đến cơ thể để kích hoạt khả năng tự phục hồi của cơ thể. Điều này làm cho Meditonsin® phù hợp với những dấu hiệu đầu tiên của cảm lạnh, chẳng hạn như ngứa cổ họng hoặc ngứa ran ở mũi." +
                                        "Nhưng ngay cả với các triệu chứng cấp tính như đau họng, ho hoặc sổ mũi, Meditonsin® vẫn thích hợp để giảm bớt các triệu chứng và làm cho tình trạng nhiễm trùng như cúm bớt khó chịu hơn. Ngoài ra, Meditonsin® có thể rút ngắn thời gian phát bệnh. Tình trạng mệt mỏi, mệt mỏi hoặc nhiệt độ cao có thể biến mất nhanh hơn." +
                                        "" +
                                        "Nghiên cứu xác nhận hiệu quả và khả năng dung nạp" +
                                        "Kết quả nghiên cứu thuyết phục có sẵn cho đến nay về thuốc nhỏ Meditonsin® với tổng số hơn 5000 bệnh nhân đã được xác nhận lại trong một nghiên cứu từ năm 2015. Nghiên cứu người dùng quy mô lớn với hơn 1.000 bệnh nhân cảm lạnh đã cho kết quả như sau:" +
                                        "" +
                                        "Các triệu chứng cảm lạnh điển hình có thể giảm nhanh chóng và đáng tin cậy khi sử dụng thuốc nhỏ Meditonsin®." +
                                        "Khoảng 90% bệnh nhân rất hài lòng hoặc hài lòng với tác dụng của thuốc nhỏ Meditonsin® và sẽ giới thiệu thuốc cho người khác." +
                                        "Về khả năng dung nạp của thuốc nhỏ Meditonsin®, hơn 97 phần trăm bệnh nhân rất hài lòng hoặc hài lòng." +
                                        "Meditonsin cũng có thể giúp ngăn ngừa cảm lạnh. Nguồn: Gerke P., Schäkermann M., 2015: Meditonsin® điều trị cảm lạnh và các bệnh nhiễm trùng giống cúm (quan sát ứng dụng trên 1.115 bệnh nhân). Pharm. Ztg., Năm thứ 160, ấn bản thứ 42: trang 44-48" +
                                        "" +
                                        "Thuốc cảm đặc biệt tốt" +
                                        "Meditonsin® thích hợp để tự mua thuốc cho trẻ từ 1 tuổi. Meditonsin® có thể được sử dụng cho trẻ sơ sinh từ 7 tháng sau khi tham khảo ý kiến \u200B\u200Bbác sĩ, vì Meditonsin® đặc biệt dung nạp tốt và nhẹ nhàng trên cơ thể.", "Meditonsin", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho Prospan ", 424830, R.drawable.a7, "200 ml", "Siro ho Prospan (kích thước gói: 200 ml) là chất tẩy ho tự nhiên giúp điều trị viêm phế quản mãn tính và viêm đường hô hấp cấp tính. Được chiết xuất từ \u200B\u200Blá thường xuân, siro ho Prospan (kích thước gói: 200 ml) giúp giải phóng phế quản khỏi chất nhầy đặc và làm dịu cơn ho." +
                                        "Do công thức tự nhiên và không chứa cồn, siro ho Prospan (kích thước gói: 200 ml) đặc biệt thích hợp cho trẻ sơ sinh và trẻ em. Siro ho Prospan (kích thước gói: 200 ml) có vị trái cây dễ chịu như anh đào dại.", "PROSPAN" ,"Đức" ));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Thuốc ho Mucosolvan 30mg/5ml", 936360, R.drawable.a8, "250 ml", "Siro ho Mucosolvan 30mg / 5ml long đờm trị ho (kích thước gói: 250 ml) được sử dụng để điều trị long đờm trong các bệnh cấp tính và mãn tính của phế quản và phổi có liên quan đến sự gián đoạn hình thành và vận chuyển chất nhầy." +
                                        "" +
                                        "Các hương liệu được thêm vào tạo cho siro ho Mucosolvan 30mg / 5ml long đờm trị ho (kích thước gói: 250 ml) có vị dâu-vani." +
                                        "" +
                                        "Để được hỗ trợ nhanh chóng với những cơn ho khan, hãy chọn Silomat. Thành phần hoạt chất có tác dụng làm dịu cơn ho khan, khó chịu, khó chịu.", "MUCOSOLVAN", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Mucosolvan", 1542750, R.drawable.a9, "50 viên", "Siro ho Mucosolvan 30mg / 5ml long đờm trị ho (kích thước gói: 250 ml) được sử dụng để điều trị long đờm trong các bệnh cấp tính và mãn tính của phế quản và phổi có liên quan đến sự gián đoạn hình thành và vận chuyển chất nhầy." +
                                        "" +
                                        "Các hương liệu được thêm vào tạo cho siro ho Mucosolvan 30mg / 5ml long đờm trị ho (kích thước gói: 250 ml) có vị dâu-vani." +
                                        "" +
                                        "Để được hỗ trợ nhanh chóng với những cơn ho khan, hãy chọn Silomat. Thành phần hoạt chất có tác dụng làm dịu cơn ho khan, khó chịu, khó chịu.", "MUCOSOLVAN", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho Bronchipret Saft", 503880, R.drawable.a10, "100 ml", "Thuốc nam trị cảm lạnh đường hô hấp." +
                                        "Để cải thiện các triệu chứng của viêm phế quản cấp tính với ho và cảm lạnh với chất nhầy đặc.", "Bionorica", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Thuốc ho Mucosolvan cho trẻ em 30mg/5ml", 197370, R.drawable.a11, "100 ml", "Siro ho trẻ em Mucosolvan 30mg / 5ml trị ho có đờm (kích thước gói: 100 ml) được sử dụng để điều trị long đờm trong các bệnh cấp tính và mãn tính của phế quản và phổi có liên quan đến sự gián đoạn hình thành và vận chuyển chất nhầy." +
                                        "" +
                                        "Để được hỗ trợ nhanh chóng với những cơn ho khan, hãy chọn Silomat. Thành phần hoạt chất có tác dụng làm dịu cơn ho khan, khó chịu, khó chịu.", "MUCOSOLVAN", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho ACC akut 600mg", 616080, R.drawable.a12,"20 viên ","Thuốc tẩy ho ACC 600mg cấp tính (kích thước gói: 20 chiếc) -là một chất tẩy ho cho cảm lạnh hoặc viêm phế quản cấp tính." +
                                        "" +
                                        "Cảm lạnh thường đi kèm với ho dai, có đờm và chảy nước mũi (nhiễm trùng xoang). Tất nhiên, nếu bạn đã bị cúm thật (nhiễm trùng giống cúm), thì cơn sốt cũng không còn xa nữa. Bạn nằm trên giường mà bủn rủn chân tay, tứ chi duỗi ra, tốt nhất là trốn vào phòng tối uống trà. Tất cả và cuối cùng những gì bạn cần là nghỉ ngơi để thư giãn, nhưng điều đó chỉ có thể thực hiện được nếu bạn không thường xuyên tỉnh táo vì cơn ho." +
                                        "" +
                                        "Vậy làm cách nào để hết ho có đờm?" +
                                        "Chất nhầy bị mắc kẹt làm tắc các lông mao của màng nhầy phế quản và ngăn không cho nó vận chuyển ra bên ngoài. Thuốc giảm ho cấp tính ACC 600mg (kích thước gói: 20 chiếc) giúp bạn làm lỏng chất nhầy và giải phóng các lông mao bị tắc của màng nhầy đường thở. Do đó, việc loại bỏ được thực hiện và cuối cùng bạn đã được giải phóng khỏi các triệu chứng đau đớn của cơn ho có đờm." +
                                        "Thuốc giảm ho cấp tính ACC 600mg (kích thước gói: 20 miếng) dễ sử dụng và có hương vị dâu đen nhẹ nhàng dễ chịu.", "ACC", "Đức" ));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Meditonsin Globuli", 528360, R.drawable.a13, "8 g", "Meditonsin Globuli (kích thước gói: 8 g) là một loại thuốc vi lượng đồng căn trị cảm lạnh. Các lĩnh vực ứng dụng có nguồn gốc từ hình ảnh thuốc vi lượng đồng căn. Chúng bao gồm: Viêm họng cấp, mũi họng. Theo các nguyên tắc của vi lượng đồng căn, mỗi phương pháp điều trị được thực hiện với một loại thuốc được điều chỉnh riêng cho bệnh nhân và bệnh cảnh lâm sàng của họ. Các biện pháp đơn vi lượng đồng căn khác nhau chắc chắn có thể được sử dụng cho các bệnh khác nhau." +
                                        "Nếu bạn không cảm thấy tốt hơn hoặc thậm chí tồi tệ hơn sau 3 ngày, hãy liên hệ với bác sĩ của bạn." +
                                        "" +
                                        "Thuốc cảm của tôi." +
                                        "Meditonsin® đã tự chứng minh mình là một loại thuốc cảm trong hơn 60 năm. \"Cổ điển\" cũng có sẵn ở dạng rắn để uống dưới dạng hạt cầu lạnh - lý tưởng để di chuyển!" +
                                        "" +
                                        "Quả cầu thực tế cũng chứa bộ ba phức hợp độc đáo chống lại cảm lạnh: sự kết hợp của ba biện pháp khắc phục tự nhiên bổ sung cho nhau." +
                                        "" +
                                        "Cuối cùng nhưng không kém phần quan trọng là điểm Meditonsin Globuli (gói: 8 g) với khả năng chịu đựng rất tốt nên thuốc cảm phù hợp cho cả gia đình (sau khi hỏi ý kiến \u200B\u200Bbác sĩ từ 7 tháng, từ 1 năm tự mua thuốc)." +
                                        "" +
                                        "Dấu hiệu đầu tiên của cảm lạnh? Meditonsin® giúp ở đây" +
                                        "Ngứa cổ họng, ngứa ran trong mũi - chúng tôi khuyên bạn nên dùng Meditonsin® Globuli khi có dấu hiệu đầu tiên của cảm lạnh. Bởi vì phức hợp tri mà nó chứa, sinh vật nhận được các xung có mục tiêu để kích hoạt khả năng tự phục hồi của cơ thể. Bằng cách này, đôi khi có thể ngăn ngừa được sự khởi đầu của các triệu chứng cảm lạnh khó chịu. Nhưng ngay cả khi đã có các triệu chứng cấp tính như đau họng, sổ mũi hoặc ho, Meditonsin® vẫn có hiệu quả tự nhiên và có thể làm dịu cơn cảm lạnh." +
                                        "" +
                                        "Đặc biệt dung nạp tốt" +
                                        "Meditonsin® đặc biệt được dung nạp tốt và bảo vệ sinh vật. Thuốc dạng cầu Meditonsin (gói: 8 g) do đó thích hợp dùng để tự mua thuốc cho trẻ từ 1 tuổi trở lên. Thuốc dạng cầu Meditonsin (kích thước gói: 8 g) có thể được sử dụng cho trẻ sơ sinh từ 7 tháng sau khi hỏi ý kiến \u200B\u200Bbác sĩ.", "MEDITONSIN", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc cảm Pinimenthol Erkältungssalbe", 577830, R.drawable.a14, "100 g", "Việc chuẩn bị là một loại thuốc thảo dược cho cảm lạnh đường hô hấp." +
                                        "Thuốc được sử dụng" +
                                        "Sử dụng bên ngoài và hít để cải thiện tình trạng cảm lạnh ở đường hô hấp (như chảy nước mũi không biến chứng, khàn giọng và ran rít phế quản không biến chứng).", "Pinimenthol", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Bronchicum Thymian Lutschtabletten", 444210, R.drawable.a15, "50 viên ", "Viên ngậm cỏ xạ hương Bronchicum (kích thước gói: 50 chiếc) chống ho một cách tự nhiên.", "Bronchicum", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho ACC akut 600mg Z ", 642600, R.drawable.a16, "20 viên ", "Thuốc giảm ho ACC 600mg Z cấp tính (kích thước gói: 20 chiếc) dùng để làm tan chất nhầy và giúp ho dễ dàng hơn trong các bệnh đường hô hấp có đờm đặc." +
                                        "" +
                                        "Cảm lạnh thường đi kèm với ho dai, có đờm và chảy nước mũi (nhiễm trùng xoang). Tất nhiên, nếu bạn đã bị cúm thật (nhiễm trùng giống cúm), thì cơn sốt cũng không còn xa nữa. Bạn nằm trên giường mà bủn rủn chân tay, tứ chi duỗi ra, tốt nhất là trốn vào phòng tối uống trà. Tất cả và cuối cùng những gì bạn cần là nghỉ ngơi để thư giãn, nhưng điều đó chỉ có thể thực hiện được nếu bạn không thường xuyên tỉnh táo vì cơn ho." +
                                        "" +
                                        "Vậy làm cách nào để hết ho có đờm?" +
                                        "Chất nhầy bị mắc kẹt làm tắc các lông mao của màng nhầy phế quản và ngăn không cho nó vận chuyển ra bên ngoài. Thuốc giảm ho ACC 600mg Z cấp tính (kích thước gói: 20 miếng) giúp bạn làm lỏng chất nhầy và giải phóng các lông mao bị tắc nghẽn của màng nhầy đường thở. Do đó, việc loại bỏ được thực hiện và cuối cùng bạn đã được giải phóng khỏi các triệu chứng đau đớn của cơn ho có đờm." +
                                        "Thuốc tẩy ho ACC akut 600mg Z (kích thước gói: 20 viên) rất dễ sử dụng và có vị chanh nhẹ nhàng dễ chịu.", "ACC", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho Silomat DMP Intensiv Kapseln ", 281010, R.drawable.a17,"12 viên", "Viên nang Silomat DMP Intensive không vị cho ho khan & ho khan (kích thước gói: 12 chiếc) từ nhà thuốc đặt hàng qua thư của bạn để điều trị ho khan có triệu chứng. Viên nang chuyên sâu Silomat DMP liều cao trị ho khan và ho khan (quy cách gói: 12 miếng) phát huy tác dụng chỉ sau 15 phút. Cơn ho khan, khó chịu sẽ được làm dịu trong một thời gian dài (khoảng 6 giờ).", "Silomat", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Meditonsin Tropfen", 508980, R.drawable.a18, "35 g", "Thuốc nhỏ giọt Meditonsin (kích thước gói: 35 g) là một loại thuốc vi lượng đồng căn đã được đăng ký. Các lĩnh vực ứng dụng có nguồn gốc từ hình ảnh thuốc vi lượng đồng căn. Chúng bao gồm: Viêm họng cấp, mũi họng. Theo các nguyên tắc của vi lượng đồng căn, mỗi phương pháp điều trị được thực hiện với một loại thuốc được điều chỉnh riêng cho bệnh nhân và bệnh cảnh lâm sàng của họ. Các biện pháp đơn vi lượng đồng căn khác nhau chắc chắn có thể được sử dụng cho các bệnh khác nhau." +
                                        "" +
                                        "Thuốc cảm của tôi" +
                                        "Thuốc đã được chứng minh hàng triệu lần trong việc điều trị cảm lạnh và các bệnh nhiễm trùng như cúm trong hơn 60 năm." +
                                        "" +
                                        "Thuốc chữa cảm lạnh Meditonsin chứa sự kết hợp thông minh của ba thành phần bổ sung, hoạt chất tự nhiên (tri-complex) chống lại cảm lạnh." +
                                        "" +
                                        "Mẹo: Phức hợp Tri-Complex duy nhất cũng có sẵn ở dạng rắn như các hạt cầu." +
                                        "Dấu hiệu đầu tiên của cảm lạnh? Meditonsin® giúp ở đây" +
                                        "" +
                                        "Meditonsin® truyền các xung động cụ thể đến cơ thể để kích hoạt khả năng tự phục hồi của cơ thể. Điều này làm cho Meditonsin® phù hợp với những dấu hiệu đầu tiên của cảm lạnh, chẳng hạn như ngứa cổ họng hoặc ngứa ran ở mũi." +
                                        "Nhưng ngay cả với các triệu chứng cấp tính như đau họng, ho hoặc sổ mũi, Meditonsin® vẫn thích hợp để giảm bớt các triệu chứng và làm cho tình trạng nhiễm trùng như cúm bớt khó chịu hơn. Ngoài ra, Meditonsin® có thể rút ngắn thời gian phát bệnh. Tình trạng mệt mỏi, mệt mỏi hoặc nhiệt độ cao có thể biến mất nhanh hơn." +
                                        "" +
                                        "Nghiên cứu xác nhận hiệu quả và khả năng dung nạp" +
                                        "Kết quả nghiên cứu thuyết phục có sẵn cho đến nay về thuốc nhỏ Meditonsin® với tổng số hơn 5000 bệnh nhân đã được xác nhận lại trong một nghiên cứu từ năm 2015. Nghiên cứu người dùng quy mô lớn với hơn 1.000 bệnh nhân cảm lạnh đã cho kết quả như sau:" +
                                        "" +
                                        "Các triệu chứng cảm lạnh điển hình có thể giảm nhanh chóng và đáng tin cậy khi sử dụng thuốc nhỏ Meditonsin®." +
                                        "Khoảng 90% bệnh nhân rất hài lòng hoặc hài lòng với tác dụng của thuốc nhỏ Meditonsin® và sẽ giới thiệu thuốc cho người khác." +
                                        "Về khả năng dung nạp của thuốc nhỏ Meditonsin®, hơn 97 phần trăm bệnh nhân rất hài lòng hoặc hài lòng." +
                                        "Meditonsin cũng có thể giúp ngăn ngừa cảm lạnh. Nguồn: Gerke P., Schäkermann M., 2015: Meditonsin® điều trị cảm lạnh và các bệnh nhiễm trùng giống cúm (quan sát ứng dụng trên 1.115 bệnh nhân). Pharm. Ztg., Năm thứ 160, ấn bản thứ 42: trang 44-48" +
                                        "" +
                                        "Thuốc cảm đặc biệt tốt" +
                                        "Meditonsin® thích hợp để tự mua thuốc cho trẻ từ 1 tuổi. Meditonsin® có thể được sử dụng cho trẻ sơ sinh từ 7 tháng sau khi tham khảo ý kiến \u200B\u200Bbác sĩ, vì Meditonsin® đặc biệt dung nạp tốt và nhẹ nhàng trên cơ thể.", "MEDITONSIN", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Umckaloabo Saft cho trẻ em", 518670, R.drawable.a19, "120 ml", "Với nước ép Umckaloabo cho trẻ em (kích thước gói: 120 ml), bạn có thể điều trị một trong những bệnh nhiễm trùng đường hô hấp phổ biến nhất - viêm phế quản cấp tính. Việc sử dụng nước ép Umckaloabo cho trẻ em (kích thước gói: 120 ml) cũng dẫn đến cải thiện các triệu chứng kèm theo của viêm phế quản, chẳng hạn như Mệt mỏi và cảm giác ốm yếu. Nước ép Umckaloabo cho trẻ em (kích thước gói: 120 ml) có thể rút ngắn quá trình của bệnh. Tái phát có thể tránh được, các khiếu nại khó chịu như cổ họng ngứa ngáy và chất nhầy đặc được giảm bớt. Ho khan dễ dàng hơn." +
                                        "" +
                                        "Nước ép Umckaloabo cho trẻ em (kích thước gói: 120 ml) chứa chiết xuất từ \u200B\u200Brễ cây Cape Pelargonium Nam Phi và thích hợp cho trẻ từ 1 tuổi trở lên nhờ khả năng dung nạp tốt. Nước ép Umckaloabo cho trẻ em (kích thước gói: 120 ml) không chứa cồn.", "Umckaloabo", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Mucosolvan Lutschpastillen 15mg", 731340, R.drawable.a20, "40 viên", "Viên ngậm Mucosolvan 15mg có tác dụng trị ho tức thì (kích thước gói: 40 viên) làm dịu cơn ho và làm lỏng chất nhầy trong cơn ho do cảm lạnh. Thông thường liên quan đến sổ mũi, đau họng và sốt, người ta bị nhiều lần. Một mặt, ho đau rát cổ họng do niêm mạc khô ráp, bị tổn thương và ngực đau theo từng cơn ho mới vì chất nhầy không muốn lỏng ra. Đây là nơi mà viên ngậm Mucosolvan 15mg giúp mang lại hiệu quả tức thì trong trường hợp ho (kích thước gói: 40 chiếc).", "Mucosolvan", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Thuốc ho Mucosolvan 30mg/5ml Schleimlöser", 438090,R.drawable.a21, "100 ml", "Nếu đường thở thô ráp và bị tấn công và ngực bị đau sau mỗi cơn ho mới do chất nhầy không thể lỏng ra, hãy dùng siro ho Mucosolvan 30mg / 5ml để trị ho (kích thước gói: 100 ml)." +
                                        "" +
                                        "Siro ho Mucosolvan 30mg / 5ml trị ho (kích thước gói: 100 ml) được sử dụng để điều trị long đờm trong các bệnh cấp tính và mãn tính của phế quản và phổi có liên quan đến sự gián đoạn hình thành và vận chuyển chất nhầy." +
                                        "" +
                                        "Để được hỗ trợ nhanh chóng với những cơn ho khan, hãy chọn Silomat. Thành phần hoạt chất có tác dụng làm dịu cơn ho khan, khó chịu, khó chịu.", "Mucosolvan", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho ACC akut 200mg ", 375360, R.drawable.a22, "20 viên ", "Thuốc giảm ho ACC 200mg cấp tính (gói 20 miếng) dùng để làm tan đờm và hỗ trợ ho trong trường hợp mắc các bệnh về đường hô hấp có đờm đặc." +
                                        "Thuốc tẩy ho ACC cấp tính 200mg (kích thước gói: 20 chiếc) -là một chất tẩy ho cho cảm lạnh hoặc viêm phế quản cấp tính." +
                                        "" +
                                        "Cảm lạnh thường đi kèm với ho dai, có đờm và chảy nước mũi (nhiễm trùng xoang). Tất nhiên, nếu bạn đã bị cúm thật (nhiễm trùng giống cúm), thì cơn sốt cũng không còn xa nữa. Bạn nằm trên giường mà bủn rủn chân tay, tứ chi duỗi ra, tốt nhất là trốn vào phòng tối uống trà. Tất cả và cuối cùng những gì bạn cần là nghỉ ngơi để thư giãn, nhưng điều đó chỉ có thể thực hiện được nếu bạn không thường xuyên tỉnh táo vì cơn ho." +
                                        "" +
                                        "Vậy làm cách nào để hết ho có đờm?" +
                                        "Chất nhầy bị mắc kẹt làm tắc các lông mao của màng nhầy phế quản và ngăn không cho nó vận chuyển ra bên ngoài. Thuốc giảm ho cấp tính ACC 200mg (kích thước gói: 20 chiếc) giúp bạn làm lỏng chất nhầy và giải phóng các lông mao bị tắc nghẽn của màng nhầy đường thở trở lại. Do đó, việc loại bỏ được thực hiện và cuối cùng bạn đã được giải phóng khỏi các triệu chứng đau đớn của cơn ho có đờm.", "ACC", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho Silomat Pentoxyverin Saft", 489090,R.drawable.a23 ,"100 ml","Siro ho làm dịu các cơn ho khan và ho khan kéo dài đến 6 giờ. Đồng thời, nước ép Silomat Pentoxyverine làm ẩm các vùng bị kích thích trong cổ họng đối với ho khan và ho khó chịu (kích thước gói: 100 ml) để cơn ho căng thẳng chấm dứt trong thời gian rất ngắn (khoảng 10 phút)." +
                                        "Nước ép Silomat Pentoxyverine có sẵn trong hiệu thuốc trực tuyến dành cho ho khan & ho khan (kích thước gói: 100 ml) không chứa cồn, đường và thuốc nhuộm, do đó lý tưởng cho trẻ em và bệnh nhân tiểu đường. Nước ép Silomat Pentoxyverine trị ho khan & ho khan (kích thước gói: 100 ml) không ảnh hưởng đến hô hấp. Do đó, nước ép Silomat Pentoxyverine cũng có thể được sử dụng làm thuốc giảm ho cho các bệnh tiềm ẩn như hen suyễn hoặc COPD cho ho khan và ho khan (kích thước gói: 100 ml).","Silomat", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Mucosolvan 15mg/2ml", 360570, R.drawable.a24, "100 ml", "Với Mucosolvan 15mg / 2ml (kích thước gói: 100 ml) bạn có thể loại bỏ chất nhầy đặc, chủ yếu được sử dụng trong các bệnh đường hô hấp, chẳng hạn như bệnh phế quản cấp tính và mãn tính và bệnh phổi xảy ra, giải quyết và tạo điều kiện cho ho. Vì Mucosolvan 15mg / 2ml (kích thước gói: 100 ml) là liệu pháp bài tiết qua đường hô hấp.", "Mucosolvan", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho Prospan ", 256530, R.drawable.a25, "100 ml", "Siro ho Prospan (kích thước gói: 100 ml) làm dịu cơn ho, thông phế quản và làm lỏng chất nhầy. Được làm trên cơ sở tự nhiên từ lá của cây thường xuân.", "PROSPAN", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Thuốc ho Ambroxol-ratiopharm", 221850, R.drawable.a26, "250 ml", "Chế phẩm là một loại thuốc để làm tan chất nhầy trong các bệnh đường hô hấp có chất nhầy đặc (long đờm)." +
                                        "Thuốc được sử dụng" +
                                        "Để điều trị long đờm các bệnh cấp tính và mãn tính của phế quản và phổi có chất nhầy đặc.", "ratiopharm", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Xi-rô ho Silomat Pentoxyverin", 379950,R.drawable.a27, "30 ml", "Xi-rô ho từ hiệu thuốc của bạn là một loại thuốc làm giảm các cơn ho khan trong thời gian rất ngắn. Chỉ sau 10 phút, có thể cảm nhận rõ tác dụng làm dịu cơn ho khan của giọt Silomat Pentoxyverine đối với ho khan và ho khan 30 ml (kích thước gói: 30 ml).", "Silomat", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Monapax Tropfen", 528360, R.drawable.a28, "20 ml","Việc chuẩn bị là một loại thuốc vi lượng đồng căn cho các bệnh của cơ quan hô hấp." +
                                        "Các lĩnh vực ứng dụng có nguồn gốc từ hình ảnh thuốc vi lượng đồng căn. Điều này bao gồm: ho.", "Monapax", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho viên uống hàng ngày Mucosolvan", 771120, R.drawable.a29, "20 viên ", "Mucosolvan viên nang giải phóng duy trì mỗi ngày một lần cho ho có đờm (kích thước gói: 20 chiếc) là một loại thuốc để làm tan chất nhầy trong các bệnh đường hô hấp có chất nhầy đặc (mucolytic). Nó được sử dụng để điều trị long đờm trong các bệnh cấp tính và mãn tính của phế quản và phổi có chất nhầy đặc.", "Mucosolvan", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Xi-rô giảm ho Phytohustil", 503880, R.drawable.a30, "150 ml", "Xi-rô giảm ho Phytohustil - thảo dược hỗ trợ tức thì cho những cơn ho khan." +
                                        "" +
                                        "Sơ lược về xi-rô giảm ho Phytohustil cho ho khan (kích thước gói: 150 ml) Sơ lược về xi-rô giảm ho Phytohustil®:" +
                                        "" +
                                        "Hoạt động ngay lập tức, có nguồn gốc từ thực vật và mạnh mẽ đối với ho khan" +
                                        "chống viêm và tái tạo nhờ sức mạnh của marshmallow" +
                                        "dung nạp rất tốt" +
                                        "thích hợp cho trẻ em từ một tuổi" +
                                        "thuần chay, không chứa gluten và lactose" +
                                        "Nhờ sức mạnh của rễ cây marshmallow, siro giảm ho thảo dược Phytohustil có tác dụng tức thì và mạnh mẽ đối với những cơn ho khan, khó chịu. Thuốc được dung nạp rất tốt và thích hợp cho trẻ em từ một tuổi." +
                                        "" +
                                        "Mẹo: Xi-rô giảm ho Phytohustil và pastilles giảm ho Phytohustil chứa các thành phần của rễ cây marshmallow và là bạn đồng hành lý tưởng cho những cơn ho khó chịu vào ban đêm, vì chúng có hiệu quả tức thì.", "Phytohustil", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Isla Cassis Pastillen", 346290, R.drawable.a31, "60 viên ", "Isla Cassis pastilles (kích thước gói: 60 miếng) được sử dụng để giảm khó chịu ở cổ họng do ho và khản giọng cũng như dây thanh được sử dụng nhiều. Isla Cassis pastilles (kích thước gói: 60 miếng) có tác dụng làm dịu và làm dịu. Isla Cassis pastilles (kích thước gói: 60 miếng) làm giảm bớt cảm giác khó chịu khó chịu, đặc biệt khi ho và khản tiếng, không khí khô và thở mũi hạn chế. Isla Cassis pastilles (kích thước gói: 60 miếng) có hương vị tươi mát dễ chịu của nho đen.","Isla", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Nước trái cây trẻ em ACC", 192270, R.drawable.a32, "100 ml", "Nước trái cây trẻ em ACC (kích thước gói: 100 ml) là một loại thuốc dùng để hóa lỏng chất nhầy đặc trong đường thở (loại bỏ ho). Nước trái cây dành cho trẻ em ACC có công dụng làm tan đờm và dễ ho trong trường hợp mắc các bệnh đường hô hấp có đờm đặc." +
                                        "" +
                                        "Cảm lạnh thường đi kèm với ho dai, có đờm và chảy nước mũi (nhiễm trùng xoang). Tất nhiên, nếu bạn đã bị cúm thật (nhiễm trùng giống cúm), thì cơn sốt cũng không còn xa nữa. Bạn nằm trên giường mà bủn rủn chân tay, tứ chi duỗi ra, tốt nhất là trốn vào phòng tối uống trà. Tất cả và cuối cùng những gì bạn cần là nghỉ ngơi để thư giãn, nhưng điều đó chỉ có thể thực hiện được nếu bạn không thường xuyên tỉnh táo vì cơn ho." +
                                        "" +
                                        "Vậy làm cách nào để hết ho có đờm?" +
                                        "Chất nhầy bị mắc kẹt làm tắc các lông mao của màng nhầy phế quản và ngăn không cho nó vận chuyển ra bên ngoài. Nước trái cây trẻ em ACC (kích thước gói: 100 ml) giúp bạn làm lỏng chất nhầy và giải phóng các lông mao bị tắc nghẽn của màng nhầy đường thở. Do đó, việc loại bỏ được thực hiện và cuối cùng bạn đã được giải phóng khỏi các triệu chứng đau đớn của cơn ho có đờm.", "ACC", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Bronchicum", 771120, R.drawable.a33, "100 ml", "Việc chuẩn bị là một loại thuốc thảo dược cho cảm lạnh đường hô hấp." +
                                        "Thuốc được sử dụng để điều trị các triệu chứng của viêm phế quản cấp tính và cảm lạnh đường hô hấp với chất nhầy đặc.", "Bronchicum", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "WICK VapoRub Erkältungssalbe", 439620, R.drawable.a34, "25 g", "Thuốc mỡ lạnh WICK VapoRub (kích thước gói: 25 g) đã là một sản phẩm lạnh được thử nghiệm và thử nghiệm cho cả gia đình trong hơn 100 năm. Hơi chữa lành của thuốc mỡ lạnh WICK VapoRub (kích thước gói: 25 g), làm giảm nghẹt mũi trong vòng 1 phút, giảm ho trong 15 phút và giảm khàn giọng và chất nhầy." +
                                        "" +
                                        "Có thể dùng dầu dưỡng lạnh để xoa hoặc xông. Nó giúp cải thiện tình trạng của các triệu chứng cảm lạnh (nhiễm trùng giống cúm) như chảy nước mũi, tắc mũi, ho, ho khó chịu và khàn giọng. Với điều này, Thuốc mỡ lạnh WICK VapoRub (kích thước gói: 25 g) mang đến một giấc ngủ đêm không bị quấy rầy, trong đó cơ thể bạn có thể phục hồi sau căng thẳng của cái lạnh.", "WICK", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho Mucosolvan Kinder 30mg/5ml", 392190,R.drawable.a35 ,"250 ml", "Nếu đường thở bị thô ráp và bị tấn công và ngực bị đau sau mỗi cơn ho mới do chất nhầy không thể lỏng ra, Mucosolvan Children’s Cough Syrup 30mg / 5ml sẽ giúp bạn chữa khỏi những cơn ho tắc nghẽn (kích thước gói: 250 ml)." +
                                        "" +
                                        "Siro ho trẻ em Mucosolvan 30mg / 5ml trị ho có đờm (kích thước gói: 250 ml) được sử dụng để điều trị long đờm trong các bệnh cấp tính và mãn tính của phế quản và phổi có liên quan đến sự gián đoạn hình thành và vận chuyển chất nhầy.", "Mucosolvan", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Meditonsin Tropfen", 978180, R.drawable.a36, "70 g ", "Thuốc nhỏ Meditonsin (kích thước gói: 70 g) là một loại thuốc vi lượng đồng căn đã được đăng ký. Các lĩnh vực ứng dụng có nguồn gốc từ các hình ảnh thuốc vi lượng đồng căn. Chúng bao gồm: Viêm họng cấp, mũi họng. Theo các nguyên tắc của vi lượng đồng căn, mỗi phương pháp điều trị được thực hiện với một loại thuốc được điều chỉnh riêng cho bệnh nhân và bệnh cảnh lâm sàng của họ. Các biện pháp khắc phục vi lượng đồng căn khác nhau chắc chắn có thể được sử dụng cho các bệnh khác nhau." +
                                        "" +
                                        "Thuốc cảm của tôi" +
                                        "Thuốc đã được chứng minh hàng triệu lần trong việc điều trị cảm lạnh và các bệnh nhiễm trùng như cúm trong hơn 60 năm." +
                                        "" +
                                        "Thuốc chữa cảm lạnh Meditonsin chứa sự kết hợp thông minh của ba thành phần bổ sung, hoạt chất tự nhiên (tri-complex) chống cảm lạnh." +
                                        "" +
                                        "Mẹo: Phức hợp Tri-Complex duy nhất cũng có sẵn ở dạng rắn như các hạt cầu." +
                                        "Dấu hiệu đầu tiên của cảm lạnh? Meditonsin® giúp ở đây", "Meditonsin", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Isla Moos Pastillen", 296310, R.drawable.a37, "60 viên ", "Isla Moos pastilles (kích thước gói: 60 miếng) có tác dụng làm dịu và làm dịu các khiếu nại trong cổ họng và dây thanh quản bị căng thẳng nặng. Isla Moos pastilles (kích thước gói: 60 miếng) đặc biệt hiệu quả trong việc chống kích ứng cổ họng và khản tiếng, không khí khô và hạn chế thở bằng mũi.", "Isla", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Bromhexin Krewel Meuselbach 12mg/ml", 410040, R.drawable.a38, "100 ml", "Để điều trị long đờm các bệnh cấp tính và mãn tính của phế quản và phổi có chất nhầy đặc.", "Bromhexin", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Soledum 100mg", 612510, R.drawable.a39,"50 viên ", "Các lĩnh vực ứng dụng của Soledum 100mg (quy cách gói: 50 viên) Viên nang Soledum được sử dụng để điều trị các bệnh mãn tính, cấp tính hoặc viêm đường hô hấp cũng như các bệnh nhiễm trùng như cúm, cảm lạnh kèm theo ho và sổ mũi, viêm phế quản và viêm xoang cấp tính và mãn tính. Lưu ý: Trong trường hợp các triệu chứng kéo dài hơn một tuần, sốt, khó thở, đờm có mủ hoặc máu, cần đến bác sĩ." +
                                        "" +
                                        "100 mg cineole" +
                                        "Chất béo trung tính chuỗi trung bình" +
                                        "Karion 83 chất khô (chứa sorbitol)" +
                                        "gelatin" +
                                        "Glycerol 85%" +
                                        "Axit clohydric 25%" +
                                        "Hypromellose phthalate" +
                                        "Dibutyl phthalate" +
                                        "Chống chỉ định Bạn không được dùng Soledum nếu bạn được biết là quá mẫn cảm với hoạt chất cineol hoặc với bất kỳ thành phần nào khác. Không có lo ngại về việc sử dụng Soledum trong thời kỳ mang thai và cho con bú. Thành phần cineole không đi vào sữa mẹ. Tuy nhiên, đối với những cân nhắc chung về an toàn, không nên dùng thuốc trong ba tháng đầu của thai kỳ. Hướng dẫn về liều lượng Uống Soledum 100mg (kích thước gói: 50 chiếc) với nhiều chất lỏng nếu có thể nửa giờ trước khi ăn. Đối với trường hợp dạ dày nhạy cảm, nên uống trong bữa ăn, trừ khi có quy định khác, người lớn và trẻ em trên 10 tuổi uống 2 viên / lần x 3 lần / ngày (trường hợp đặc biệt cứng đầu thì uống 2 viên / lần x 4 lần / ngày). Để điều trị thêm hoặc lâu dài: 2 viên x 2 lần / ngày Đối với trẻ em dưới 10 tuổi: 1 viên x 3 lần / ngày. Soledum 100mg (gói: 50 chiếc) cũng thích hợp cho bệnh nhân tiểu đường.", "Soledum", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Bronchicum Elixir", 434520, R.drawable.a40, "100 ml", "Bronchicum Elixir (kích thước gói: 100 ml) từ Klosterfrau từ hiệu thuốc đặt hàng qua thư của bạn là một loại thuốc thảo dược giúp chống lại các bệnh và các bệnh về đường hô hấp. Trong thời gian bị cảm lạnh, Bronchicum Elixir (kích thước gói: 100 ml) giúp làm giảm các triệu chứng của viêm phế quản cấp tính và cảm lạnh đường hô hấp với chất nhầy đặc một cách tối ưu. Bronchicum Elixir hương vị tuyệt vời (kích thước gói: 100 ml) chứa một công thức được chọn lọc từ dầu cỏ xạ hương và các thành phần hoạt tính từ cây anh thảo.", "Bronchicum","Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Thuốc ho dạng bột Acc 600 mg",686970,R.drawable.a41, "20 viên ", "Acc direkt 600 mg dạng bột dùng để uống dạng gói (gói: 20 chiếc) là thuốc tẩy ho để hóa lỏng dịch tiết ở phế quản giúp giảm ho trong trường hợp mắc các bệnh hô hấp cấp tính ở người lớn." +
                                        "Cảm lạnh thường đi kèm với ho dai, có đờm và chảy nước mũi (nhiễm trùng xoang). Tất nhiên, nếu bạn đã bị cúm thật (nhiễm trùng giống cúm), thì cơn sốt cũng không còn xa nữa. Bạn nằm trên giường mà bủn rủn chân tay, tứ chi duỗi ra, tốt nhất là trốn vào phòng tối uống trà. Tất cả và cuối cùng những gì bạn cần là nghỉ ngơi để thư giãn, nhưng điều đó chỉ có thể thực hiện được nếu bạn không thường xuyên tỉnh táo vì cơn ho." +
                                        "" +
                                        "Vậy làm cách nào để hết ho có đờm?" +
                                        "Chất nhầy bị mắc kẹt làm tắc các lông mao của màng nhầy phế quản và ngăn không cho nó vận chuyển ra bên ngoài. Acc direkt 600 mg dạng bột để uống trong một gói (kích thước gói: 20 chiếc) giúp bạn bằng cách làm lỏng chất nhầy và giải phóng các lông mao bị tắc của màng nhầy đường thở. Do đó, việc loại bỏ được thực hiện và cuối cùng bạn đã được giải phóng khỏi các triệu chứng đau đớn của cơn ho có đờm.", "ACC","Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Fagusan Lösung", 439620, R.drawable.a42, "200 ml", "Thuốc là một chất làm tan chất tiết để thúc đẩy sự tống xuất chất tiết phế quản (long đờm) và được sử dụng để làm lỏng chất nhầy trong khu vực phế quản và tạo điều kiện cho ho" +
                                        "Cảm lạnh và nhiễm trùng giống cúm" +
                                        "Viêm phế quản (viêm phế quản)", "Fagusan", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "WICK Xi-rô ho mật ong", 414630, R.drawable.a43,"120 ml ", "Siro ho WICK chống ho khan với mật ong (kích thước gói: 120 ml) có tác dụng chống lại các cơn ho khan không hiệu quả nhanh và kéo dài. Điều này đặc biệt xảy ra với hoặc sau khi bị cảm lạnh, nhiễm trùng giống như cúm và catarrh phế quản. Trong siro ho WICK trị ho khan với mật ong (kích thước gói: 120 ml), hoạt chất Wick đã được chứng minh kết hợp với đặc tính kháng viêm của mật ong.", "WICK","Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Pinimenthol Erkältungsbalsam mild", 459510, R.drawable.a44, "50 g", "Pinimenthol Cold Balm nhẹ (kích thước gói: 50 g) là một loại thuốc thảo dược được chỉ định cho các bệnh đường hô hấp có chất nhầy đặc do cảm lạnh. Bạn có thể sử dụng Pinimenthol Cold Balm nhẹ (kích thước gói: 50 g) dưới dạng hít hoặc thoa ngoài.", "Pinimenthol", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Isla Junior Pastillen", 187170, R.drawable.a45, "20 viên", "Isla Junior pastilles (kích thước gói: 20 chiếc) được sử dụng để điều trị nhắm mục tiêu cảm lạnh và đau họng điển hình, chẳng hạn như đau họng và kích ứng cổ họng. Isla Junior pastilles (gói 20 miếng) thích hợp cho trẻ từ 4 tuổi. Isla Junior pastilles (gói 20 miếng) đặc biệt phù hợp với sở thích đặc biệt của trẻ em. Các loại pastilles cố tình làm không có hương vị nhân tạo và có hương vị thơm ngon như dâu tây. Isla Junior Pastilles (kích thước gói: 20 miếng) cũng chứa vitamin C, kẽm và canxi pantothenate: Isla Junior pastilles (kích thước gói: 20 miếng) không chứa đường, có vị ngọt của cỏ ngọt và thuần chay.", "Isla", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"BROMHEXIN 8 Berlin-Chemie", 236640, R.drawable.a46, "50 viên ", "Thuốc là thuốc tiêu phế quản (thuốc làm tan chất nhầy trong các bệnh đường hô hấp có chất nhầy đặc), được dùng để làm long đờm trong các bệnh cấp và mãn tính của phế quản và phổi có chất nhầy đặc.", "BROMHEXIN", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Silomat DMP Lutschpastillen Zitrone 10,5mg bei trockenem Husten", 538560, R.drawable.a47, "40 viên ", "Ho khan hay ho khan chỉ đơn giản là không cho cơ thể nghỉ ngơi: Ban ngày cơ thể căng thẳng và đầu óc căng thẳng và ban đêm nó cướp đi giấc ngủ ngon của bạn. Để bạn có thể nhanh chóng tìm thấy sự bình yên và thư thái, điều quan trọng là tránh những cơn ho không đáng có. Bởi vì với mỗi lần ho, màng nhầy nhạy cảm trong phế quản bị kích thích và kích thích ho càng mạnh hơn. Với Viên ngậm chanh Silomat DMP 10.5mg trị ho khan (gói 40 viên), cảm giác ho sẽ giảm hẳn. Bằng cách này, cơn ho sẽ được dập tắt và màng nhầy nhạy cảm trong đường thở không bị tổn thương. Và khi khả năng ho giảm đi, cơ thể bạn cũng đi vào trạng thái nghỉ ngơi, cho phép các màng nhầy lành lại một cách tự nhiên. Một cơn ho cần thiết vẫn còn, chẳng hạn như nếu nuốt phải.", "Silomat", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Gói siro ho Mucosolvan", 395250, R.drawable.a48, "2X 5 ml", "Gói siro ho Mucosolvan khi di chuyển khi bị ho (kích thước gói: 21X5 ml) chứa thành phần hoạt chất ambroxol hydrochloride. Thuốc này được sử dụng để điều trị long đờm, ho có đờm trong các bệnh cấp tính hoặc mãn tính của phế quản và phổi có liên quan đến sự hình thành chất nhầy bất thường và sự vận chuyển chất nhầy bị suy giảm. Gói siro ho Mucosolvan được chỉ định cho người lớn và thanh thiếu niên từ 12 tuổi trở lên.", "Mucosolvan", "Đức"));

                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,   "Paracetamol ratiopharm 500mg", 113220, R.drawable.a50, "20viên", "Paracetamol ratiopharm 500mg (kích thước gói: 20 chiếc) được sử dụng để điều trị triệu chứng đau và sốt từ nhẹ đến vừa phải." +
                                        "Paracetamol ratiopharm 500mg (gói 20 miếng) là thuốc giảm đau, hạ sốt (giảm đau và hạ sốt.)",  "Paracetamol" ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,    "Babix-Inhalat N", 236640, R.drawable.a51, "10ml", "Babix-Inhalat N (kích thước gói: 10 ml) được sử dụng để điều trị cảm lạnh đường hô hấp kèm theo nhiều chất nhầy. Tinh dầu từ lá bạch đàn và lá vân sam giúp thở dễ dàng hơn, giảm kích ứng niêm mạc và cải thiện tình trạng ho ra đờm dai." +
                                        "" +
                                        "Babix-Inhalat N (kích thước gói: 10 ml) là một loại thuốc hoàn toàn từ thảo dược và được phát triển đặc biệt cho trẻ sơ sinh từ 3 tháng, nhưng cũng lý tưởng để sử dụng cho trẻ lớn hơn và người lớn. "    ,"MICKAN"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Osanit Globuli zuckerfrei ", 336180, R.drawable.a52, "7.5g", "Osanit Globuli không đường (kích thước gói: 7,5 g) là một phương thuốc vi lượng đồng căn và được sử dụng để giảm đau như chuột rút có thể xảy ra khi trẻ sơ sinh hoặc trẻ nhỏ đang mọc răng. Đi kèm với kích thích và bồn chồn, kết hợp với sốt, chuột rút, tiêu chảy và mất ngủ có thể được giảm bớt với sự trợ giúp của Osanit Globuli không đường (kích thước gói: 7,5 g). Bạn tiết kiệm cho con những giờ không thoải mái, điều này có thể cướp đi giấc ngủ ngon và quý giá của bạn, đặc biệt là vào ban đêm. Với Globuli không đường Osanit Globuli (kích thước gói: 7,5 g), bạn và con bạn có thể ngủ lại cả đêm một cách thoải mái và yên bình.", "Osanit"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Antiscabiosum 25%", 1062840, R.drawable.a53, "200 g", "Antiscabiosum 25% (kích thước gói: 200 g) là một chất chống ký sinh trùng để sử dụng trên da." +
                                        "Cái này được dùng để làm gì?" +
                                        "Được sử dụng để điều trị bệnh ghẻ ở người lớn, như một tác nhân ít độc hại hơn, thay thế cho thuốc chống giun sán đã được nghiên cứu kỹ lưỡng.", "Strathmann"   ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Carum Carvi Baby-kümmelzäpfchen", 385050, R.drawable.a54, "10viên", "Carum Carvi Baby caraway đạn (gói kích thước: 10) là một loại thuốc vi lượng đồng căn với chiết xuất từ \u200B\u200Bhạt caraway tự nhiên", "Pädia"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "NUROFEN Junior Fieber- & Schmerzsaft Erdbeer ", 248880, R.drawable.a55, "100ml", "Sử dụng Nurofen Junior Fever and Pain Juice Strawberry 40mg / ml để điều trị các cơn đau và sốt nhẹ đến vừa cho trẻ từ 6 tháng. Với công thức mới của NUROFEN Junior Fever & Pain Juice Strawberry (kích thước gói: 100 ml), giờ đây con bạn chỉ phải nuốt một nửa lượng nước ép để có hiệu quả tương tự. Ống tiêm đo kèm theo giúp bạn dễ dàng xác định liều lượng chính xác theo độ tuổi và cân nặng của trẻ.", "Nurofen"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,    "Thuốc ho cho trẻ em Mucosolvan 30mg/5ml", 197370, R.drawable.a56, "100ml", "Siro ho trẻ em Mucosolvan 30mg / 5ml trị ho có đờm (kích thước gói: 100 ml) được sử dụng để điều trị long đờm trong các bệnh cấp tính và mãn tính của phế quản và phổi có liên quan đến sự gián đoạn hình thành và vận chuyển chất nhầy." +
                                        "" +
                                        "Để được hỗ trợ nhanh chóng với những cơn ho khan, hãy chọn Silomat. Thành phần hoạt chất có tác dụng làm dịu cơn ho khan, khó chịu, khó chịu.", "Mucosolvan"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,    "Engelwurzbalsam Baby & Kinder",588030, R.drawable.a57, "15g","Dầu dưỡng Angelica dành cho trẻ em và trẻ em (kích thước gói: 15 g) từ Casida là một chất hỗ trợ đã được chứng minh cho chứng sổ mũi nhạy cảm.", "Casida"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,    "Pinimenthol Erkältungsbad", 657390, R.drawable.a58, "190ml", "Pinimenthol Cold Bath cho Trẻ từ 2 tuổi Eucalyptus (kích thước gói: 190 ml) là một loại thuốc thảo dược, được chỉ định cho các bệnh về đường hô hấp có chất nhầy nhớt do cảm lạnh." +
                                        "" +
                                        "Phương thức hành động" ,"Pinimenthol"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,    "Babix Baby Thymianbad", 410040, R.drawable.a59, "125 ml", "Babix Baby Thyme Bath (kích thước gói: 125 ml) là phụ gia tắm thích hợp cho trẻ từ 3 tháng tuổi. Cỏ xạ hương đã được sử dụng cho các bệnh viêm phế quản, ho khan, ho gà và ho co giật cho các lứa tuổi. Với phụ gia tắm này, khả năng chữa bệnh tự nhiên của cỏ xạ hương có lợi cho cả những đứa trẻ nhỏ của chúng ta.", "Babix"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,   "Viburcol N Kinder- und Säuglings-suppositorien",513570, R.drawable.a60, "12 viên", "Thuốc vi lượng đồng căn cho sự bồn chồn." +
                                        "Các lĩnh vực ứng dụng có nguồn gốc từ hình ảnh thuốc vi lượng đồng căn. Chúng bao gồm: Tình trạng bồn chồn liên quan đến bệnh ở trẻ sơ sinh và trẻ nhỏ. Để cải thiện các triệu chứng cảm lạnh ở trẻ sơ sinh và thời thơ ấu." +
                                        "Lưu ý: Trong trường hợp khó thở, sốt, khạc đờm có mủ hoặc máu, cũng như đau bụng dai dẳng, không rõ ràng hoặc mới xuất hiện thì nên đi khám bác sĩ, vì đây có thể là những bệnh cần được điều trị y tế.", "Viburcol"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,     "Multilind Heilsalbe mit Nystatin",741540, R.drawable.a61, "50 g","Thuốc mỡ chữa bệnh đa hướng với nystatin (gói kích thước: 50 g) giúp chữa viêm da và màng nhầy, đau do kích ứng cơ học (chó sói), các nốt đỏ, ngứa và rát ở các nếp gấp trên cơ thể, ở mông và vùng ngực cũng như giữa đùi, ví dụ: B. Hăm tã.","Multilind"   ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,   "Contramutan Junior Sirup ", 533460, R.drawable.a63, "150 ml", "Contramutan Junior Syrup (kích thước gói: 150 ml) là một loại thuốc vi lượng đồng căn được sử dụng để điều trị cảm lạnh như sốt và các bệnh nhiễm trùng giống như cúm, viêm và các vết thương ở mũi và cổ họng. Contramutan Junior Syrup (kích thước gói: 150 ml) cũng thích hợp để điều trị dự phòng khi tăng nguy cơ nhiễm trùng." , "Contramutan"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Sinusitis Hevert Sl Tabletten",787440, R.drawable.a64, "100 viên","Thuốc trị viêm xoang Hevert Sl dạng viên (gói: 100 miếng) là thuốc vi lượng đồng căn trị viêm tai mũi họng. Các lĩnh vực ứng dụng có nguồn gốc từ hình ảnh thuốc vi lượng đồng căn. Chúng bao gồm: viêm tai mũi họng và các xoang cạnh mũi (viêm xoang)." +
                                        "" +
                                        "Để có một mũi thông thoáng - Viên uống trị viêm xoang Hevert SL." +
                                        "Thuốc trị viêm xoang Hevert SL chống lại tình trạng viêm nhiễm ở mũi và xoang." +
                                        "" +
                                        "Chống viêm" +
                                        "Thuốc long đờm" +
                                        "Dung nạp tốt" , "Sinusitis"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Dermaveel Creme", 533460, R.drawable.a65, "30 ml", "Da là cơ quan lớn nhất của chúng ta. Nó hoạt động giống như một lớp áo bảo vệ khỏi nóng, lạnh, mầm bệnh và các tác động bên ngoài khác. Ở những người bị viêm da thần kinh, chàm hoặc da rất thô ráp, lớp bảo vệ tự nhiên này bị suy giảm chức năng. Da bị ngứa, mẩn đỏ và khô." +
                                        "" +
                                        "Nhiều người muốn giảm đau mà không sử dụng cortisone. Với kem Dermaveel (kích thước gói: 30 ml), điều này có thể thực hiện được trong nhiều trường hợp. Dermaveel Cream (kích thước gói: 30 ml) hỗ trợ hiệu quả và bền vững cho quá trình tái tạo của da, đồng thời phục hồi khả năng lưu giữ độ ẩm của da. Các triệu chứng như ngứa, da ửng đỏ và da sần sùi sẽ biến mất. Hàng rào bảo vệ da cũng được củng cố - và cùng với đó là khả năng chống lại các kích thích bên ngoài. Dermaveel Cream (gói kích thước: 30 ml) giúp mọi người, từ trẻ em đến người già, những người bị viêm da thần kinh, eczema hoặc da khô với ngứa nghiêm trọng. Và điều đó không có cortisone." , "Dermaveel"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,   "NUROFEN Junior Fieber- & Schmerzsaft Erdbeer", 330990, R.drawable.a66, "150 ml","Nurofen Junior Fever-u.schmerzsaft Erdbe.40 mg / ml có thể được dùng cho trẻ từ 6 tháng (từ 7 kg) để điều trị triệu chứng ngắn hạn đối với cơn đau và sốt từ nhẹ đến vừa phải." , "Nurofen"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Immun Loges Saft",954210, R.drawable.a67, "150 ml" , "Nước trái cây Immun Loges (kích thước gói: 150 ml) là thực phẩm chức năng bổ sung các chất tự nhiên đặc biệt, vitamin và các nguyên tố vi lượng." +
                                        "" +
                                        "Chiết xuất đặc biệt Spirulina phân đoạn thu được trong một quy trình đặc biệt do Đại học Mississippi phát triển và do đó có sẵn ở nồng độ cao." +
                                        "1,3- / 1,6-β-glucans (rất tinh khiết, thu được từ nấm Hiratake) chứa các hợp chất phân tử 1,3- / 1,6." +
                                        "Vitamin C và D cũng như selen và kẽm cung cấp các chất dinh dưỡng cơ bản cần thiết cho chức năng của các tế bào miễn dịch.", "Loges"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,     "Mirfulan",823650, R.drawable.a68, "100 g","Mirfulan (kích thước gói: 100 g) hoạt động rất đơn giản - để vết thương mau lành hơn!" +
                                        "" +
                                        "Oxit kẽm và dầu gan cá là thành phần của Mirfulan (kích thước gói: 100 g). Để nâng cao hiệu quả hơn nữa, Mirfulan (kích thước gói: 100 g) cũng được làm giàu với vitamin A và D." , "Mirfulan"   ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Kneipp Erkältungsbad spezial",637500,R.drawable.a69, "200 ml",  "Đặc biệt tắm nước lạnh Kneipp (kích thước gói: 200 ml) giúp tăng cường sức khỏe và được sử dụng như một phương pháp hỗ trợ điều trị cảm lạnh. Chất nhầy ứ đọng được nới lỏng để việc thở trở lại dễ dàng hơn. Cùng với việc ngâm mình trong bồn nước ấm, Kneipp Cold Bath Special (kích thước gói: 200 ml) cũng mang đến cơ hội để có được giây phút thư giãn tăng cường. Cơ thể được nghỉ ngơi và do đó thu thập năng lượng cần thiết cho quá trình phục hồi sắp tới.","Kneipp"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Otovowen Tropfen zum Einnehmen",1013370,R.drawable.a70, "50 ml"  ,"Chế phẩm này là một loại thuốc vi lượng đồng căn cho bệnh viêm tai giữa." +
                                        "Các lĩnh vực ứng dụng có nguồn gốc từ hình ảnh thuốc vi lượng đồng căn." +
                                        "Bao gồm: cải thiện các triệu chứng viêm tai giữa, sổ mũi.", "Otovowen"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Kinderimmun Doktor wolz Pulver", 774180, R.drawable.a71, "65 g" , "Phức hợp hoạt tính trong bột Kinderimmun Doktor wolz (kích thước gói: 65 g) bao gồm các thành phần tự nhiên và có giá trị khác nhau cho một hệ thống miễn dịch khỏe mạnh. Để tăng cường hệ thống miễn dịch, Dr. Nghiên cứu của Wolz đã phát triển một sự kết hợp miễn dịch cho cả gia đình, phụ nữ mang thai và cho con bú." +
                                        "" +
                                        "Bột Kinderimmun Doktor wolz (kích thước gói: 65 g) được khuyên dùng cho những trường hợp căng thẳng và tăng trưởng đặc biệt. Vitamin D đặc biệt cần thiết cho sự phát triển lành mạnh và phát triển xương khỏe mạnh ở trẻ em." ,"Doktor wolz"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Transpulmin Baby Balsam mild",385050, R.drawable.a72, "40 ml","Transpulmin Baby Balsam nhẹ (kích thước gói: 40 ml) giải phóng đường thở khỏi chất nhầy cứng và giảm ho. Công thức cân bằng và dịu nhẹ được phát triển đặc biệt cho trẻ mới biết đi và trẻ sơ sinh từ 3 tháng." +
                                        "Transpulmin Baby Balsam nhẹ (kích thước gói: 40 ml) được dùng để thoa lên ngực và lưng. Nó làm lỏng chất nhầy bị mắc kẹt, giúp thở dễ dàng hơn và tăng cường sức khỏe." ,"Transpulmin"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Prospan Hustensaft",256530,R.drawable.a73,"100 ml","Siro ho Prospan (kích thước gói: 100 ml) làm dịu cơn ho, thông phế quản và làm lỏng chất nhầy. Được làm trên cơ sở tự nhiên từ lá của cây thường xuân." ,"Prospan"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Dobensana Junior 1,2mg/0,6mg",271320,R.drawable.a74, "24 viên","Dobensana Junior 1,2mg / 0,6mg (quy cách gói: 24 viên) là viên ngậm giúp giảm đau họng nhanh chóng. Do tác dụng chống vi khuẩn, mầm bệnh bị tiêu diệt sớm và ngăn ngừa tình trạng viêm nhiễm niêm mạc miệng. Được phát triển đặc biệt cho trẻ em, Dobensana Junior 1,2mg / 0,6mg (kích thước gói: 24 chiếc) không chứa bất kỳ đường hoặc phẩm màu nhân tạo nào. Viên ngậm tan dễ dàng trong miệng và có vị dâu, được trẻ em ưa chuộng."  ,"Dobensana"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Holle Bio Anfangsmilch 1 auf Ziegenmilchbasis",744090 ,R.drawable.a75,"400 g", "Sữa công thức sữa dê hữu cơ Holle 1 (kích thước gói: 400 g) thích hợp cho trẻ từ sơ sinh. Bạn chỉ nên cân nhắc sử dụng sữa ban đầu nếu trẻ không được bú mẹ hoặc không được bú đủ sữa.","Holle"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Bigaia Tropfen", 1288770,R.drawable.a76,"10 ml", "Bạn có thể sử dụng Bigaia drops (gói kích thước: 10 ml) cho trẻ sơ sinh đau bụng kèm theo khóc nhiều ở trẻ bú mẹ. Vì Bigaia drops (kích thước gói: 10 ml) không ảnh hưởng đến việc cho con bú."  ,"Bigaia"        ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Beecraft Propolis Kapseln Plus", 1187790,R.drawable.a77, "60 viên", "Beecraft Propolis Capsules Plus (kích thước gói: 60 miếng) được định lượng cao và chứa 170 mg keo ong, được lấy từ 320 mg keo ong thô tự nhiên. Ngoài ra, nguyên tố vi lượng kẽm được bao gồm để hỗ trợ cơ thể và hệ thống miễn dịch trong quá trình gắng sức, căng thẳng hoặc trong mùa lạnh. Viên nang keo ong Plus được dung nạp tốt và thích hợp để sử dụng lâu dài và phòng ngừa.", "Propolis"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Holle Bio Folgemilch 3 auf Ziegenmilchbasis Pulver ",744090, R.drawable.a78, "400 g","Sữa bột","Holle"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Anabox 7 Tage Regenbogen ",692070,R.drawable.a80,"1 viên" , "Anabox 7 ngày cầu vồng (kích thước gói: 1 chiếc) được sử dụng để lưu trữ và sắp xếp thuốc. 4 lần tuyển sinh \"sáng, trưa, tối, tối\" và một môn học bổ sung.", "Anabox"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Babix-Inhalat N",661980, R.drawable.a81,"20ml","Babix-Inhalat N (kích thước gói: 20 ml) được sử dụng để điều trị cảm lạnh đường hô hấp kèm theo chất nhầy mạnh. Tinh dầu từ lá bạch đàn và lá vân sam giúp thở dễ dàng hơn, giảm kích ứng niêm mạc và cải thiện tình trạng ho ra đờm dai." +
                                        "" +
                                        "Babix-Inhalat N (kích thước gói: 20 ml) là một loại thuốc hoàn toàn từ thảo dược và được phát triển đặc biệt cho trẻ sơ sinh từ 3 tháng, nhưng cũng lý tưởng để sử dụng cho trẻ lớn hơn và người lớn.", "Babix"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "NUROFEN Junior Fieber- & Schmerzsaft Orange",251430, R.drawable.a82, "100ml","Sử dụng Nurofen Junior Fever and Pain Juice Orange 40mg / ml để điều trị các cơn đau nhẹ đến trung bình cũng như sốt cho trẻ từ 6 tháng. Với công thức mới của NUROFEN Junior Orange Fever & Pain Juice (kích thước gói: 100 ml), giờ đây con bạn chỉ phải nuốt một nửa lượng nước ép để có hiệu quả tương tự. Ống tiêm đo kèm theo giúp bạn dễ dàng xác định liều lượng chính xác theo độ tuổi và cân nặng của trẻ.","NUROFEN"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Esprico Kaukapseln",963900,R.drawable.a83,"120 viên","Khó tập trung có thể do nhiều nguyên nhân khác nhau - một trong số đó là do chế độ ăn uống thiếu chất! Viên nhai Esprico (kích thước gói: 120 miếng) cung cấp một khái niệm dinh dưỡng bổ sung được thiết kế đặc biệt cho trẻ em từ 5 tuổi bị rối loạn tập trung và chú ý, ADD và ADHD." +
                                        "Viên nhai Esprico vị trái cây (gói 120 viên) có hàm lượng cao các chất quan trọng, cần thiết cho các chức năng khỏe mạnh của não và do đó hỗ trợ trí nhớ.","Esprico"        ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Norsan Omega-3 Kids Jelly",1698810,R.drawable.a84,"120 viên"  ,"Omega-3 KIDS Jelly là những giọt thạch dẻo dai thơm ngon với vị dâu và chanh rất dễ ăn." +
                                        "" +
                                        "Những giọt này đã được phát triển để nhai và đặc biệt cho nhu cầu của trẻ em. Đặc biệt, trẻ em rất khó ăn cá vài lần một tuần hoặc thậm chí hàng ngày. Đó là lý do tại sao dạng bào chế thông qua ống nhỏ giọt nhai ngon là một sự thay thế tuyệt vời cho trẻ em. Chất lượng cao và độ tươi của nguyên liệu đảm bảo hương vị ngon." +
                                        "" +
                                        "Cho dù đang di chuyển, đi du lịch, trong thời gian dài hay cho cả gia đình - gói cung cấp Omega-3 KIDS Jelly của chúng tôi mang đến sự thay thế lý tưởng cho bạn và con bạn. Gói cung cấp của chúng tôi chứa 120 miếng nhai thả.","Norsan"     ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Babix-Inhalat N",311100,R.drawable.a85,"5 ml" ,"Babix-Inhalat N (kích thước gói: 5 ml) được sử dụng để điều trị cảm lạnh đường hô hấp kèm theo nhiều chất nhầy. Tinh dầu từ lá bạch đàn và lá vân sam giúp thở dễ dàng hơn, giảm kích ứng niêm mạc và cải thiện tình trạng ho ra đờm dai." +
                                        "" +
                                        "Babix-Inhalat N (kích thước gói: 5 ml) là một loại thuốc hoàn toàn từ thảo dược và được phát triển đặc biệt cho trẻ sơ sinh từ 3 tháng, nhưng cũng lý tưởng để sử dụng cho trẻ lớn hơn và người lớn.","Babix"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Antiscabiosum 10% cho trẻ em",1260720,R.drawable.a86, "200g","chất chống ký sinh trùng để sử dụng trên da." +
                                        "Cái này được dùng để làm gì?" +
                                        "Được sử dụng để điều trị bệnh ghẻ ở trẻ em trên 6 tuổi, như một tác nhân ít độc hại hơn, như một chất thay thế cho thuốc chống giun sán đã được nghiên cứu đầy đủ." +
                                        "Trẻ em từ 1 đến 6 tuổi chỉ có thể được điều trị bằng thuốc" +
                                        "nếu không có thêm tổn thương da có thể thúc đẩy sự hấp thụ của hoạt chất benzyl benzoat từ da vào cơ thể và" +
                                        "nếu việc điều trị được thực hiện dưới sự kiểm tra y tế cẩn thận.", "Strathmann"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Osa Schorf Kopfgneis Spray",736440,R.drawable.a87,"30 ml","Osa vảy gneiss dạng xịt (kích thước gói: 30 ml) được sử dụng để điều trị viêm da tiết bã ở trẻ sơ sinh (ISD), gneiss đầu, thường được gọi là nắp nôi.","Osa"        ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,  "Vitamin D3 Hevert Tabletten",379950,R.drawable.a88,"100 viên","Viên nén Vitamin D3 Hevert (kích thước gói: 100 miếng) có thể được dùng dự phòng nếu có nguy cơ thiếu vitamin D. Viên Vitamin D3 Hevert (gói 100 miếng) cũng thích hợp để ngăn ngừa còi xương cho trẻ sơ sinh. Viên nén Vitamin D3 Hevert (gói: 100 viên) mua từ nhà thuốc đặt qua đường bưu điện cũng thích hợp để hỗ trợ điều trị loãng xương.","Hevert"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Pinimenthol Erkältungsbalsam mild",459510,R.drawable.a89,"50g","Pinimenthol Cold Balm nhẹ (kích thước gói: 50 g) là một loại thuốc thảo dược được chỉ định cho các bệnh đường hô hấp có chất nhầy đặc do cảm lạnh. Bạn có thể sử dụng Pinimenthol Cold Balm nhẹ (kích thước gói: 50 g) dưới dạng hít hoặc thoa ngoài." ,"Pinimenthol"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Nasentropfen AL 0,05%",93330,R.drawable.a90, "10 ml" ,"Chế phẩm này là một phương tiện để sử dụng trong mũi (tác nhân tê giác) và chứa xylometazoline alpha-giao cảm." +
                                        "Xylometazoline có đặc tính co mạch và do đó làm thông mũi niêm mạc." +
                                        "Thuốc được sử dụng" +
                                        "để làm thông mũi niêm mạc (điều trị ngắn hạn) trong trường hợp cảm lạnh, sổ mũi (viêm mũi vận mạch) hoặc viêm mũi dị ứng (viêm mũi dị ứng)" +
                                        "để tạo điều kiện thuận lợi cho việc tiết dịch tiết trong viêm xoang cạnh mũi cũng như catarrh của tai giữa ống dẫn trứng liên quan đến viêm mũi. Trong trường hợp này, thuốc chỉ nên được sử dụng theo chỉ định của bác sĩ." +
                                        "Việc chuẩn bị dành cho trẻ em từ 2 đến 6 tuổi.","NASENTROPFEN AL"       ,"Đức"));
                                //sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4,"Isla Junior Pastillen",187170,R.drawable.a91,"20 viên" ,"Isla Junior pastilles (kích thước gói: 20 chiếc) được sử dụng để điều trị nhắm mục tiêu cảm lạnh và đau họng điển hình, chẳng hạn như đau họng và kích ứng cổ họng. Isla Junior pastilles (gói 20 miếng) thích hợp cho trẻ từ 4 tuổi. Isla Junior pastilles (gói 20 miếng) đặc biệt phù hợp với sở thích đặc biệt của trẻ em. Các loại pastilles cố tình làm không có hương vị nhân tạo và có hương vị thơm ngon như dâu tây. Isla Junior Pastilles (kích thước gói: 20 miếng) cũng chứa vitamin C, kẽm và canxi pantothenate: Isla Junior pastilles (kích thước gói: 20 miếng) không chứa đường, có vị ngọt của cỏ ngọt và thuần chay.","Isla"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Bachblüten Kinder Kl.träumerle Globuli noctu dr.bach ",644130,R.drawable.a92,"10 g","Nếu bạn được biết là quá mẫn cảm với bất kỳ thành phần nào ở trên thì không nên sử dụng sản phẩm này.","Bachblüten"        ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Dolormin für Kinder Ibuprofensaft 40mg/ml ",246840,R.drawable.a93, "100 ml","Chế phẩm này là thuốc giảm đau, chống viêm và hạ sốt (thuốc chống viêm / chống viêm không steroid, NSAID)." +
                                        "Nó được sử dụng tại" +
                                        "đau nhẹ đến trung bình - chẳng hạn như đau đầu và đau răng." +
                                        "Sốt." +
                                        "Sử dụng cho trẻ em nặng từ 10 kg trở lên (1 tuổi trở lên), thanh thiếu niên và người lớn","Dolormin"      ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Esprico 1x1 Suspension",1018980,R.drawable.a94,"120 ml","Esprico 1x1 huyền phù (kích thước gói: 30X4 ml) được sử dụng như một chế độ ăn uống cân bằng để điều trị chế độ ăn uống của các rối loạn tăng động giảm chú ý (ADD, ADHD), có liên quan đến khó tập trung và học tập.","Esprico"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Nurofen Junior Fiebersaft Erdbeer",169320,R.drawable.a95,"100 ml", "cho cơn đau nhẹ đến vừa phải" +
                                        "nếu bạn bị sốt" +
                                        "thích hợp cho bé từ 6 tháng có trọng lượng cơ thể từ 5kg trở lên","Nurofen"    ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Neo Angin junior Halsschmerzssaft",489090,R.drawable.a96, "100 ml", "Nước ép trị đau họng Neo Angin Junior (kích thước gói: 100 ml) làm dịu màng nhầy của mũi họng và giảm đau họng.", "Neo Angin"       ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, "Multi Mam Kompressen",494190,R.drawable.a97,"12 viên", "Túi chườm Multi Mam (số lượng gói: 12 miếng) đảm bảo cho mẹ và con bú thoải mái. Túi chườm Multi Mam được các bà mẹ khuyên dùng (kích thước gói: 12 chiếc) giúp giảm sưng và xoa dịu núm vú bị đau. Nén Multi Mam (kích thước gói: 12 miếng) bảo vệ chống lại mầm bệnh và do đó ngăn ngừa nhiễm trùng (viêm vú). Băng ép Multi Mam do Ardo Medical sản xuất (kích thước gói: 12 miếng) phát huy hiệu quả nhanh chóng và thúc đẩy quá trình chữa lành vết thương tự nhiên." ,"Multi Mam"      ,"Đức"));



                                //Hãng 5: Mediamarkt
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "KOENIC KVC 3221 A Staubsauger", 3768390, R.drawable.m1,"700 watt","Máy hút bụi này không bao giờ làm bẩn sàn nhà, mà làm việc tận tâm cho đến cùng: KOENIC KVC 3221 A mạnh mẽ!" +
                                        "" +
                                        "KOENIC đã thiết kế nó với hai màu trắng và xanh để thiết bị không chỉ đáp ứng tốt chức năng của nó mà còn đẹp mắt. Công suất tối đa là 700 watt. Nếu thiết bị đang chạy, việc cung cấp năng lượng cho thiết bị được thực hiện thông qua kết nối cáp với nguồn điện lưới. Chiều dài của cáp có thể được đo là 5000 mm. Theo đó, giá trị độ dài này cũng là bán kính hoạt động của sản phẩm.","KOENIC", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PHILIPS FC 9331/09 PowerPro Compact", 4314090,R.drawable.m2, "900 watt" , "Động cơ 900 W cho công suất hút mạnh" +
                                        "Đầu phun TriActive thực hiện ba bước làm sạch trong một lần vận hành" +
                                        "Công nghệ PowerCyclone 5 loại bỏ bụi khỏi không khí" +
                                        "Khớp nối ActiveLock để dễ dàng thích ứng với mọi quy trình làm sạch" +
                                        "Bánh xe lớn để kiểm soát chuyển động chất lỏng" +
                                        "Dễ dàng làm trống chỉ bằng một tay" +
                                        "Bàn chải mềm tích hợp trong tay cầm, luôn sẵn sàng để sử dụng" +
                                        "Hệ thống lọc dị ứng H13 bắt hơn 99,9% bụi mịn" +
                                        "Công suất hút cao và các vòi phun hiệu suất cao giúp loại bỏ tới 99,9% tất cả các hạt bụi","PHILIPS", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi SIEMENS VS06A111",3201780,R.drawable.m3,"600 watt","Hiệu suất mạnh mẽ và dễ sử dụng. Hút bụi nhanh chóng và dễ dàng chỉ với một động tác." +
                                        "powerSecure System: Cho hiệu suất làm sạch lâu dài, ngay cả khi túi đang đầy" +
                                        "Thể tích túi bụi XL lớn giúp giảm thiểu việc thay đổi túi bụi và tiết kiệm chi phí theo dõi" +
                                        "Cáp dài cho bán kính hoạt động 9 m giúp giảm các thay đổi ổ cắm khó chịu" +
                                        "Bộ lọc vệ sinh cho khí thải sạch. Đạt được mức phát thải bụi B.","SIEMENS", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,"máy hút bụi MIELE Complete C3",11384730,R.drawable.m4,"550 watt","Động cơ tiết kiệm điện với hiệu suất được cải thiện" +
                                        "Làm sạch tuyệt vời và hiệu quả nhờ đầu phun EcoTeQ-Plus" +
                                        "Bộ lọc không khí làm sạch giảm tiếng ồn" +
                                        "Thoải mái và linh hoạt với phụ kiện ba phần tích hợp" +
                                        "Đặc biệt yên tĩnh và nhẹ nhàng - Bánh xe DynamicDrive", "MIELE", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi BOSCH BGC05A220A",3815820,R.drawable.m5  ,"700 watt","Nhỏ gọn với hiệu suất làm sạch ấn tượng." +
                                        "Mạnh mẽ, sạch sẽ, nhanh chóng. Không có túi. Không có chi phí theo dõi" +
                                        "" +
                                        "Siêu nhỏ gọn: Đặc biệt thiết thực để sử dụng - trọng lượng thực sự nhẹ chỉ 4,4 kg" +
                                        "" +
                                        "EasyStorage: Rất nhiều tiện lợi ngay cả sau khi hút bụi. Nhờ Kho lưu trữ ống dễ dàng và kích thước nhỏ gọn, thiết bị có thể được cất gọn nhanh chóng và dễ dàng, tiết kiệm không gian. Điều này để lại nhiều không gian hơn cho những thứ khác.", "BOSCH"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi PHILIPS FC 9741/09",8134500, R.drawable.m6,"750 watt","Máy hút bụi không túi Philips PowerPro Expert FC9741 / 09 gây ấn tượng với hệ thống khóa dị ứng, đảm bảo rằng bụi vẫn bị kẹt bên trong. Kết hợp với bộ lọc dị ứng, loại bỏ tới 99,9% tất cả các hạt bụi, bao gồm mạt bụi nhà, lông động vật và phấn hoa, nó là sản phẩm lý tưởng cho những người bị dị ứng. Công nghệ PowerCyclone 8 độc quyền đảm bảo kết quả làm sạch tuyệt vời, giúp tăng tốc không khí trong buồng lốc xoáy một cách đáng kể để tách nó khỏi bụi một cách hiệu quả. Nhờ có vòi phun TriActive + cải tiến, 3 bước công việc được thực hiện trong một lần: Trong khi ngay cả những tấm thảm dài cũng được hút bụi hoàn toàn, các rãnh dẫn không khí ở phía trước sẽ hút các mảnh vụn lớn. Bàn chải bên cạnh làm sạch sát tường và đồ đạc cùng một lúc.", "PHILIPS"     , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi BOSCH BGL3B110",3630690, R.drawable.m7 ,"650 watt","Máy hút bụi dạng hộp BGL 3 B 110" +
                                        "Hiệu suất làm sạch triệt để tương đương với máy hút bụi 2200 watt * - với mức tiêu thụ điện năng đặc biệt thấp" +
                                        "Đặc biệt thực tế để sử dụng - trọng lượng nhẹ chỉ 4,3 kg" +
                                        "Bộ lọc vệ sinh cho khí thải sạch. Đạt được mức phát thải bụi B." +
                                        "Hệ thống PowerProtect: Cho hiệu suất làm sạch lâu dài, ngay cả khi túi đang đầy" +
                                        "Ít thay đổi túi bụi hơn và giảm chi phí theo dõi nhờ thể tích túi bụi lớn 4l", "BOSCH" , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi AEG VX 7-2-IW-S",7136430, R.drawable.m8 ,"650 watt","Máy hút bụi xi lanh AEG VX 7-2-IW-S" +
                                        "VX7 kết hợp bán kính hoạt động lớn với công suất hút cao và tiếng ồn vận hành thấp. Hoàn hảo cho nhu cầu cao." +
                                        "" +
                                        "Hút bụi thuận tiện với phạm vi 12 m" +
                                        "VX7 có phạm vi cực lớn 12 mét, vì vậy bạn có thể dọn dẹp nhiều phòng một cách đơn giản và dễ dàng chỉ trong một lần." +
                                        "Nắp truy cập 180 ° thực tế" +
                                        "Truy cập hoàn toàn vào túi bụi và bộ lọc nhờ nắp truy cập 180 °. Điều này làm cho túi bụi và bộ lọc đặc biệt dễ tiếp cận." +
                                        "Có thể thay đổi nhờ Công nghệ chuyển động 360 ° ™" +
                                        "Bánh xe 360 \u200B\u200B° Motion Technology ™ cho khả năng di chuyển và cơ động tối đa. VX7 của chúng tôi được trang bị bánh xe xoay 360 °, rất lý tưởng để lái xe quanh chướng ngại vật." +
                                        "Hệ thống nhiều phòng ™" +
                                        "để linh hoạt hơn và bán kính hành động tối đa" +
                                        "Silence Pro System ™ để làm sạch êm hơn" +
                                        "Đặc biệt yên tĩnh nhờ Hệ thống Silence Pro System ™ để hút bụi với độ ồn thấp trong mọi môi trường. Vòi hút sàn hút đặc biệt yên tĩnh - với hiệu suất cực mạnh" , "AEG"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi ROWENTA RO3953EA",3728100, R.drawable.m9 , "750 watt","Bụi được hút lên bằng máy hút bụi xi lanh được thu gom một cách hợp vệ sinh trong một túi đựng bụi được cung cấp cho mục đích này, rất dễ vứt bỏ. Khi lắp túi bụi, sản phẩm mang lại cho bạn một chút linh hoạt, vì túi thuộc loại túi thay thế SWIRL, túi đựng bụi Rowenta ZR200520 và túi đựng bụi Rowenta ZR200720 (chống mùi) được chấp nhận. Có màn hình hiển thị giúp bạn cập nhật mức bụi." +
                                        "" +
                                        "Kiểm tra các chức năng tuyệt vời và hoạt động hiệu quả của máy hút bụi ROWENTA RO3953EA Compact Power!","ROWENTA"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi PHILIPS FC 9555/09",6851340, R.drawable.m10,"750 watt","Hiệu suất tối đa nhờ PowerCyclone 7. Loại bỏ bụi đơn giản và hợp vệ sinh." +
                                        "Máy hút bụi không túi Philips Power Pro Active FC 9555/09 PowerPro Active mang lại hiệu suất làm sạch tuyệt vời. Đầu phun TriActive + cải tiến đảm bảo hút bụi tối đa trên các bề mặt khác nhau nhờ các lỗ hở của nó. Tùy thuộc vào nhiệm vụ làm sạch, hiệu suất của máy hút bụi có thể được điều chỉnh riêng bằng cách sử dụng bộ điều chỉnh. Chỉ cần tháo hộp chứa bụi ra, có thể tránh được phần lớn sự hình thành đám mây bụi khi đổ. Hình dáng nhỏ gọn của thiết bị giúp tiết kiệm không gian lưu trữ.","PHILIPS"     , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi BOSCH BGL3B112",3994830, R.drawable.m11,"650 watt","Công suất hút và tuổi thọ: máy hút bụi BOSCH BGL3B112 biết cách thuyết phục bằng những lý lẽ đúng đắn!" +
                                        "" +
                                        "Màu đen của thiết bị khá tinh tế nên trông khá kín đáo. Với công suất 650 watt, việc làm sạch rất chuyên sâu. Thiết bị phải được kết nối với nguồn điện trong quá trình hoạt động. Bạn có thể sử dụng thiết bị trong bán kính 10 m, vì bán kính hoạt động của nó mở rộng đến mức đó." +
                                        "" +
                                        "Sản phẩm được xây dựng dưới dạng máy hút bụi xe trượt băng thông dụng. Ống kim loại kính thiên văn có thể được kéo dài một cách tiện lợi với chiều dài rất ấn tượng. Điều này giúp việc vệ sinh trở nên dễ dàng hơn và dễ dàng hơn ở mặt sau, ngay cả đối với những người dùng rất cao. Công suất âm của máy là 79 dB (A)." +
                                        "" +
                                        "Có một bộ lọc chức năng cao (bộ lọc vệ sinh) để không khí hoàn hảo rời khỏi máy hút bụi. Màn hình hiển thị cho bạn biết thời điểm thay đổi bộ lọc." +
                                        "" +
                                        "Túi được sử dụng để hứng bụi. Loại G Tất cả phải được sử dụng làm túi đựng bụi." +
                                        "" +
                                        "Hãy làm cho công việc dọn dẹp trở nên dễ dàng cho chính bạn và sở hữu máy hút bụi BGL3B112!","BOSCH"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi THOMAS 788.563 Pet & Family",10602900, R.drawable.m12 ,"1700 watt","Dù là hút bụi khô hay lau ướt, THOMAS PET & FAMILY đều làm sạch mạnh mẽ và hiệu quả." +
                                        "Với công suất 1600 watt mạnh mẽ, nó có thể dễ dàng loại bỏ các vết bẩn trên vải bọc và thảm. Nhờ hệ thống áp lực phun đặc biệt, dung dịch làm sạch được phun sâu với áp lực và nước bẩn được hút lại một cách mạnh mẽ cùng một lúc. Để làm sạch sâu và vệ sinh sợi. Cũng như cách hút bụi thông thường, lông động vật, các chất gây dị ứng và các hạt bụi bẩn được kết dính trong nước và được thải bỏ một cách đơn giản cùng với nước bẩn. Không tạo ra bụi khi đổ nước lọc. Để có một không khí xả được làm mới đáng kể. Và tất cả những thứ này MADE IN GERMANY.","THOMAS"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi MIELE Complete C3 Cat&Dog",12632700, R.drawable.m13  ,"890 watt ","Động cơ tiết kiệm điện với hiệu suất được cải thiện","MIELE"  , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi MIELE Blizzard CX1 Cat&Dog",15904350, R.drawable.m14,"890 watt ","Hiệu suất làm sạch mạnh mẽ nhờ công nghệ Vortex - 890 W.","MIELE"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi SIEMENS VSZ3XTRM11",5512080, R.drawable.m15 ,"850 watt","Máy hút bụi này không chịu thua bất kỳ sàn bẩn nào: SIEMENS VSZ3XTRM11!" +
                                        "" +
                                        "Đối với một thiết bị làm sạch, nó không quá nổi bật ở phía trước với màu đen của nó. Thiết bị được cung cấp công suất 850 watt để có thể đạt được công suất hút mạnh. Thiết bị phải được kết nối với nguồn điện trong quá trình hoạt động. Cáp nguồn có chiều dài 8000 mm. Bán kính hoạt động cũng được xác định trên cơ sở độ dài này." +
                                        "" +
                                        "Về thiết kế, thiết bị là máy hút bụi dạng xi lanh. Vì máy hút bụi có ống lồng kim loại, bạn có thể dễ dàng làm sạch bên dưới ghế sâu và tủ. Nếu bạn cần tạm nghỉ việc hút bụi, bạn có thể tận dụng hệ thống đỗ xe. Ống hút chỉ đơn giản là móc vào máy hút bụi, và sau đó sẵn sàng để đưa tay và an toàn để sử dụng tiếp. Công suất âm của thiết bị là 77 dB (A)." +
                                        "" +
                                        "Đặc biệt đối với những người bị dị ứng, việc sử dụng bộ lọc HEPA là điều vô cùng dễ chịu, bởi vì ngay cả những hạt nhỏ nhất cũng không bị thải ngược trở lại không khí." +
                                        "" +
                                        "Vòi có kẽ hở cung cấp cho sản phẩm thêm sức mạnh làm sạch. Ngoài ra còn có một bàn chải kết hợp thích hợp cho cả thảm và sàn cứng. Công suất hút có thể được điều chỉnh bằng điện tử." +
                                        "" +
                                        "Túi được sử dụng để hứng bụi. Giống SWIRL S 67 phải được dùng làm túi đựng bụi. Túi lọc của thiết bị có thể tích 4 lít và do đó cung cấp đủ không gian." +
                                        "" +
                                        "Lưu lượng gió là 41 lít / giây. cực kỳ mạnh mẽ." +
                                        "" +
                                        "Không có thiết bị nào khác, bạn sẽ có được kết quả làm sạch tuyệt vời như vậy: đừng bỏ lỡ máy hút bụi SIEMENS VSZ3XTRM11!","SIEMENS"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi BOSCH BGS5A300", 9843000, R.drawable.m16 ,"700 watt","Bosch BGS5A300 để hút bụi tiết kiệm năng lượng." +
                                        "Bosch BGS5A300 biết cách thuyết phục: Việc dễ dàng tháo và đổ hộp chứa bụi, một dụng cụ hỗ trợ đỗ xe và đỗ xe cũng như một tay cầm cao cấp tiện dụng giúp hút bụi dễ dàng tạo điều kiện thuận lợi cho việc vận hành. Máy hút bụi cũng hoạt động hiệu quả. Nhờ hệ thống QuattroPower với công nghệ hiệu suất cao, bạn đạt được kết quả làm sạch tuyệt vời với mức tiêu thụ năng lượng thấp (EEK A)","BOSCH"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi SIEMENS VSZ3B212",4241160, R.drawable.m17,"700 watt","Máy hút bụi này không bao giờ làm bẩn sàn nhà, mà hoạt động tận tâm cho đến cùng: SIEMENS VSZ3B212 Z 3.0 mạnh mẽ! Với màu đen tuyền, sản phẩm không có vẻ ngoài quá cầu kỳ mà gây ấn tượng với thiết kế đơn giản. Thiết bị được cung cấp 700 watt để có thể đạt được công suất hút mạnh. Thiết bị phải được kết nối với nguồn điện trong quá trình hoạt động. Với cáp kết nối dài 7000 mm, bán kính chức năng được xác định rõ ràng. Bán kính hoạt động cũng được xác định trên cơ sở độ dài này. Thiết kế di động của thiết bị có nghĩa là nó rất linh hoạt. Ống lồng dẫn bụi được làm bằng kim loại. Nếu bạn cần tạm nghỉ việc hút bụi, bạn có thể tận dụng hệ thống đỗ xe. Ống hút chỉ đơn giản là móc vào máy hút bụi, và sau đó sẵn sàng để đưa tay và an toàn để sử dụng tiếp. Về âm lượng, người ta có thể cho rằng công suất âm thanh là 79 dBA. Có một bộ lọc chức năng cao (bộ lọc vi mô) để không khí hoàn hảo rời khỏi máy hút bụi. Các bàn chải và vòi phun sau đây có sẵn để làm sạch các khu vực cụ thể: vòi phun có kẽ hở, vòi sàn cứng, vòi bọc và vòi lăn có thể chuyển đổi. Bụi được bắt trong túi và có thể được loại bỏ hoàn toàn mà không để lại cặn. Loại G ALL phải được sử dụng làm túi đựng bụi. Hãy biến chiếc máy hút bụi SIEMENS VSZ3B212 Z 3.0 mạnh mẽ của riêng bạn và bạn sẽ không phải thất vọng!","SIEMENS"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi PHILIPS FC8782/09",9396240, R.drawable.m18, "750 watt","Máy hút bụi" +
                                        "Máy hút bụi Performer Silent FC8782 / 09 có túi từ Philips gây ấn tượng với công nghệ vòi phun tiên tiến giúp hút sạch bụi bẩn và bụi mịn." +
                                        "Đầu phun TriActive Pro loại bỏ ngay cả những hạt bụi nhỏ nhất, để đảm bảo làm sạch đặc biệt kỹ lưỡng." +
                                        "Hơn 99,99% bụi mịn và chất gây dị ứng được ghi nhận, lý tưởng cho những người bị dị ứng." +
                                        "Mặc dù hiệu suất cao nhưng nó vẫn ghi điểm với mức tiêu thụ thấp." +
                                        "Nhờ bàn chải lát gỗ, sàn an toàn không bị trầy xước.","PHILIPS"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi KÄRCHER 1.081-130.0 SE 4001",8858190, R.drawable.m19 ,"1400 watt","Dù là bụi bẩn hay bụi bẩn ẩm ướt: máy hút bụi khô và ướt KÄRCHER 1.081-130.0 SE 4001 hoạt động hiệu quả và hút hoàn toàn mọi loại bụi bẩn!" +
                                        "" +
                                        "Với thiết kế màu vàng, thiết bị mang đến một mảng màu sống động cho công việc dọn dẹp đôi khi nhàm chán. Năng lượng khổng lồ của nó được cung cấp cho máy hút bụi với công suất 1400 watt. Định nghĩa chính xác, sản phẩm là máy hút bụi. Để thiết bị hoạt động, nó phải được kết nối trực tiếp với nguồn điện. Cáp kết nối dài 7500 mm. Chiều dài của cáp đương nhiên cũng xác định bán kính hoạt động." +
                                        "" +
                                        "Với thiết kế như một máy hút bụi dạng xi lanh (thùng / nồi hơi), mỗi thùng có một thùng chứa nước sạch và đã qua sử dụng. Nhà sản xuất đã chọn nhựa cho vật liệu vỏ máy. Về âm lượng, có thể giả sử công suất âm là 74 d B (A)." +
                                        "" +
                                        "Máy được trang bị bộ lọc (lọc bọt) cực mịn." +
                                        "" +
                                        "Các bàn chải và vòi phun sau đây có sẵn để làm sạch các khu vực cụ thể: vòi sàn, vòi có kẽ hở, vòi bọc và vòi rửa có gắn bề mặt cứng." +
                                        "" +
                                        "Với tổng thể tích 4 lít, két nước chắc chắn rất thuyết phục. Nhờ lưu lượng gió 70 lít / giây. máy hút bụi có tốc độ lấy nước tuyệt vời." +
                                        "" +
                                        "Đảm bảo sàn nhà sạch sẽ, sáng bóng: máy hút bụi khô / ướt 1.081-130.0 SE 4001. Hãy đánh nó!","KÄRCHER"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi AEG VX 6-2-IW-5",5370300, R.drawable.m20 , "800 watt","Máy hút bụi VX6-2-IW-5 xi lanh" +
                                        "Làm sạch mạnh mẽ trên tất cả các lớp phủ sàn và xử lý dễ dàng nhờ Công nghệ chuyển động 360 °" +
                                        "" +
                                        "Movable - Bánh xe 360 \u200B\u200B° Motion Technology ™ cho khả năng cơ động rất tốt. VX6 được trang bị bánh xe xoay 360 ° lý tưởng để lái xe quanh chướng ngại vật." +
                                        "Nắp truy cập 180 ° thuận tiện - truy cập đầy đủ vào túi bụi và bộ lọc nhờ nắp truy cập 180 °. Điều này làm cho túi bụi và bộ lọc đặc biệt dễ tiếp cận." +
                                        "Power Pro System ™ giúp làm sạch tất cả các sàn một cách dễ dàng - VX6 với Power Pro System ™ đảm bảo kết quả làm sạch triệt để. Hệ thống Power Pro ™ được trang bị động cơ mạnh mẽ và vòi phun sàn DustPro ™ hiệu quả cao, cho phép làm sạch toàn diện, cả trên thảm và trên sàn cứng","AEG"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi FAKIR Red Vac",2770830, R.drawable.m21,"700 watt","Dù lớp bụi dày đến đâu, máy hút bụi FAKIR Red Vac sẽ không bỏ cuộc cho đến khi hoàn thành công việc và sàn nhà sạch sẽ!" +
                                        "" +
                                        "Bạn có thể nhận ra từ phối màu đỏ và đen mà nhà sản xuất đã đưa vào thiết kế một chút ý tưởng. Thiết bị được cung cấp 700 watt để có thể đạt được công suất hút mạnh. Thiết bị phải được kết nối với nguồn điện trong quá trình hoạt động. Thiết bị hút có bán kính hoạt động lên đến 8 m." +
                                        "" +
                                        "Chiều dài của ống hút có thể thay đổi vì nó có chức năng ống lồng. Nếu bạn cần tạm nghỉ việc hút bụi, bạn có thể tận dụng hệ thống đỗ xe. Ống hút chỉ đơn giản là móc vào máy hút bụi, và sau đó sẵn sàng để đưa tay và an toàn để sử dụng tiếp. Nếu bạn đo công suất âm thanh, bạn nhận được giá trị lên đến 74 dB (A)." +
                                        "" +
                                        "Với bộ lọc của thiết bị (bộ lọc EPA), không có hạt nào được để lại trong không khí." +
                                        "" +
                                        "Có sẵn một số bàn chải và đầu phun (đầu hút sàn, đầu hút kẽ hở, bàn chải đồ nội thất và vòi bọc vải) để có thể tiếp cận và làm sạch các loại sàn khác nhau." +
                                        "" +
                                        "Hệ thống thu gom dựa trên việc sử dụng các túi có thể được lấy ra và xử lý khi chúng đã đầy. Loại Swirl Y293 phải dùng làm túi đựng bụi. Thể tích túi lọc là ba lít. Có chỉ báo lấp đầy bụi để bạn luôn biết lượng bụi đã được hút vào." +
                                        "" +
                                        "Máy hút bụi vượt trội về chất lượng và hiệu suất: hãy để máy hút bụi Red Vac thuyết phục bạn!","FAKIR"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi BISSELL 2024N",2203200, R.drawable.m22 ,"450 watt","Dọn dẹp nhanh chóng khắp ngôi nhà: Máy hút bụi nhẹ BISSELL Featherweight Pro Eco 2-trong1" +
                                        "BISSELL Featherweight Pro Eco là máy hút bụi lốc xoáy mạnh mẽ với các bộ lọc có thể giặt được, lý tưởng để làm sạch bụi bẩn trên sàn cứng trong nhà. Nó có thể dễ dàng chuyển đổi thành một máy hút bụi cầm tay đa năng và hoàn hảo để làm sạch nhanh chóng cầu thang, đồ nội thất bọc, rèm cửa và xe hơi của bạn." +
                                        "" +
                                        "Tính chất:" +
                                        "Máy hút bụi có dây nhẹ, linh hoạt, hoàn hảo để dọn dẹp nhanh chóng xung quanh nhà" +
                                        "Hiệu suất tuyệt vời trên sàn cứng" +
                                        "Chuyển đổi nhanh chóng thành máy hút bụi cầm tay đa năng" +
                                        "Đặc tính nhẹ và không có túi khiến máy hút bụi trở thành một thiết bị hoàn hảo cho các công việc dọn dẹp thông thường và nhanh chóng" ,"BISSELL"  , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi KÄRCHER 1.081-140.0 SE 4002",11153190, R.drawable.m23,"1400 watt","Máy hút bụi cũng có thể được sử dụng để làm sạch những thứ khó làm sạch, chẳng hạn như đồ nội thất bọc hoặc nệm. Điều tốt nhất cần làm là tải KÄRCHER 1.081-140.0 SE 4002!" +
                                        "" +
                                        "Đôi khi hút bụi có thể là một hoạt động mệt mỏi. Không phải như vậy với thiết bị này! Nhờ vẻ ngoài màu vàng sống động, bạn luôn có một chút điểm nhấn thị giác sẽ khiến bạn phải chú ý. Năng lượng khổng lồ của nó được cung cấp cho máy hút bụi với công suất 1400 watt. Sản phẩm được định nghĩa chính xác là máy hút bụi. Nếu thiết bị đang chạy, nguồn cung cấp năng lượng của thiết bị được đảm bảo thông qua kết nối cáp với nguồn điện lưới." +
                                        "" +
                                        "Máy làm sạch là một máy hút bụi sàn và do đó có thể được kéo qua phòng trên ống. Nhà sản xuất đã chọn nhựa cho vật liệu vỏ máy." +
                                        "" +
                                        "Có một bộ lọc chức năng cao (bộ lọc bọt) để không khí hoàn hảo rời khỏi máy hút bụi." +
                                        "" +
                                        "Các bàn chải và vòi phun sau có sẵn để làm sạch các khu vực cụ thể: vòi sàn, vòi kẽ hở, vòi bọc vải, vòi rửa có gắn bề mặt cứng và vòi rửa để làm sạch vải bọc." +
                                        "" +
                                        "Bình chứa nước của thiết bị có dung tích 4 lít. Nhờ lưu lượng gió 70 lít / giây. máy hút bụi có tốc độ lấy cao." +
                                        "" +
                                        "Để làm sạch vải bọc hoặc thảm nhạy cảm một cách cẩn thận nhưng nhẹ nhàng: hãy tấn công và tận dụng chức năng đa năng của máy hút bụi 1.081-140.0 SE 4002!","KÄRCHER"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "máy hút bụi SIEMENS VSZ7A400 Z 7.0",8211000, R.drawable.m24 , "700 watt","Siemens VSZ7A400 để làm sạch hoàn hảo." +
                                        "Siemens VSZ7A400 với túi đựng bụi PowerProtect mang lại lợi thế hơn so với các máy hút bụi khác. Điều này đảm bảo hiệu suất cao ngay cả khi túi đang đầy. Điều này cũng giảm thiểu nhu cầu thay túi bụi, do đó bạn tiết kiệm chi phí theo dõi. Đồng thời, máy hút bụi còn đạt được mức phát thải bụi A, nhờ bộ lọc AllergyPlus. Với bộ lọc này, ngay cả những hạt nhỏ nhất như phấn hoa hoặc chất gây dị ứng cũng được giữ lại, do đó Siemens VSZ7A400 cũng rất lý tưởng cho những người bị dị ứng.","SIEMENS"   , "Đức"));



                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PKM S3-90 ABTZ", 7751490   , R.drawable.m25 ,"900mmx320mm","Với máy hút mùi PKM S3-90 ABTZ bạn mang công nghệ tuyệt vời và thiết kế xinh xắn vào gian bếp!" +
                                        "" +
                                        "Với máy hút mùi treo tường, bạn được hưởng lợi từ các tùy chọn lắp đặt linh hoạt giúp làm việc hiệu quả. Làm sạch vỏ thép không gỉ là đơn giản. Ngoài ra, vật liệu có khả năng chống chịu rất tốt và phù hợp với mọi môi trường. Vì tính kinh tế của nó, thiết bị được xếp vào loại hiệu suất năng lượng A. Bạn có thể mong đợi mức tiêu thụ điện trung bình thấp là 39 kWh mỗi năm. Là một thành phần hiệu quả hơn nữa, đèn LED được gắn vào mô hình, đảm bảo điều kiện ánh sáng dễ chịu, rất kinh tế và có đặc điểm là tuổi thọ dài. Vì đèn thuộc loại ưu việt nên chúng được xếp vào loại hiệu suất chiếu sáng A. Một lần chạm ngón tay là đủ để vận hành sản phẩm." +
                                        "" +
                                        "Để máy chiết xuất hoàn toàn phù hợp với nhà bếp của bạn, thiết kế của nó được làm bằng thép không gỉ cổ điển. Để tránh không khí bị ép vào bếp từ bên ngoài qua máy hút mùi, nó được trang bị một cánh đảo gió ngược." +
                                        "" +
                                        "Một tài sản thực sự cho thiết bị nhà bếp của bạn!","PKM"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "GORENJE EC5341WG",16852950, R.drawable.m26,"70 L", "Với bếp từ âm trần GORENJE EC5341WG, bạn có thể dễ dàng tạo ra những món ăn ngon!" +
                                        "" +
                                        "Thiết bị độc lập hoàn toàn bổ sung cho thiết bị nhà bếp của bạn một cách hoàn hảo. Hiệu suất năng lượng loại A cũng hoàn toàn thuyết phục. Bạn có thể chuẩn bị bữa ăn nhanh chóng và dễ dàng trên bếp điện ngay cả khi không có kết nối gas. Nhờ 4 vùng nấu, bạn có thể chế biến các món ăn khác nhau cùng một lúc. Lò nướng cũng là một phần của bếp và mang đến cho bạn cơ hội để tạo ra những chiếc bánh ngọt, bánh quy hoặc nướng thơm ngon. Thể tích của khoang nướng là 70 lít. Vì bụi bẩn khó có cơ hội bám trên bề mặt men nhẵn nên vật liệu này đã được sử dụng cho các bức tường bên trong lò nướng. Đây là cách lý tưởng để ngăn ngừa béo và co. Các tính năng sau cũng rất ấn tượng: cửa kéo kính thiên văn đơn, kính hai lớp, AquaClean, C (Cửa nhỏ gọn), ổ cắm nướng HomeMADE 70 L. độc đáo, cửa kéo GentleClose và SuperSize." +
                                        "" +
                                        "Không phải thời tiết thích hợp để đốt than nướng trong vườn? Sau đó chỉ cần sử dụng chức năng nướng thực tế của thiết bị này. Thịt giòn và các món nướng có thể được thực hiện nhanh chóng. Món rang có mất quá nhiều thời gian để rã đông từ tủ đông không? Sau đó, bạn có tùy chọn sử dụng chức năng rã đông thực tế với thiết bị này. Cài đặt quan trọng nhất đối với hầu hết các lò là nhiệt trên và dưới. Tất nhiên, mô hình này cũng có chức năng phổ biến và đảm bảo rằng các món ăn của bạn luôn ngon. Không khí tuần hoàn là sự bổ sung cổ điển cho nhiệt trên và dưới cổ điển; nó rút ngắn thời gian nướng cho nhiều món ăn vì nhiệt được phân bổ đều trong bên trong. Với chỉ báo nhiệt dư, bạn có một hệ thống kiểm soát tuyệt vời. Điều này sẽ thông báo cho bạn rằng bếp vẫn còn nóng. Điều khiển thời gian điện tử giúp bạn vận hành thiết bị dễ dàng hơn. Một bộ hẹn giờ được tích hợp như một sự bổ sung tiện lợi. Ngoài ra còn có một màn hình hiển thị thời gian." +
                                        "" +
                                        "Đơn giản là không thể đánh bại: EC 5341 WG từ GORENJE!","GOREN"     , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BAUKNECHT HEKO 500 H",23503350, R.drawable.m27  ,"65 L","Bộ bếp Bauknecht HEKO 500 H bao gồm:" +
                                        "Bếp nấu sẵn Bauknecht có màn hình hiển thị và thủy phân (65 lít)" +
                                        "MỤC 4" +
                                        "" +
                                        "Lò quạt với 8 chức năng" +
                                        "Chức năng làm sạch thủy phân" +
                                        "Thể tích không gian nấu 65 lít" +
                                        "Đồng hồ điện tử điều khiển cảm ứng" +
                                        "Bếp gốm thủy tinh Bauknecht (60 cm)" +
                                        "MỤC 4" +
                                        "" +
                                        "4 vùng nấu, bao gồm 1 vùng rang hai vòng, 1 vùng nấu DUO hai vòng" +
                                        "Khung thép không gỉ phẳng" +
                                        "Chỉ báo nhiệt dư 4 chiều" +
                                        "Năng lượng điều tiết","BAUKNECHT"  , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PKM EB-GK-2R",3672000, R.drawable.m28 ,"rộng 290mm","Nếu bạn thích nấu nướng và tạo bất ngờ cho những người thân yêu của mình với những thực đơn ngon, bạn chắc chắn nên xem xét kỹ hơn bếp PKM EB-GK-2R." +
                                        "" +
                                        "Để thiết bị hòa hợp hoàn hảo với môi trường xung quanh, thiết kế của nó là màu đen và thép không gỉ. Hai bếp có sẵn để bạn chuẩn bị bữa ăn của mình. Bếp gốm không làm nóng toàn bộ vùng nấu mà chỉ làm nóng bề mặt đế nồi của bạn. Điều này giúp tiết kiệm năng lượng và nhiệt, đồng thời đặc biệt hiệu quả. Căn bếp của bạn trở nên quý phái qua bề mặt gốm thủy tinh cao cấp. Nó cũng làm cho sản phẩm dễ dàng chăm sóc và hiệu quả." +
                                        "" +
                                        "Bất kể luộc, hấp hay rang - với bếp từ PKM, mọi thứ đều có thể!","PKM"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "NEFF BCR5525N - B55CR25N0", 45849000, R.drawable.m29 ,"71 L","Lò nướng tích hợp SLIDE & HIDE® - vào lò miễn phí" +
                                        "Nhiệt kế thịt SinglePoint - chỉ cần theo dõi nhiệt độ" +
                                        "" +
                                        "Hide® - cửa lò có thể thu vào hoàn toànCircoTherm® - hệ thống khí nóng Neff tuyệt vời để nướng, rang và nấu đồng thời ở 3 cấp độ Bộ đôi để làm sạch hoàn hảo: Nhiệt phân sau khi sử dụng nhiều - nội thất lò tự động làm sạch.EasyClean® - lựa chọn thân thiện với môi trường cho làm sạch nhanh chóng ở giữa.","NEFF"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "OK. OMW 2223 DS Mikrowelle",3505740, R.drawable.m30 , "700 watt","OK không chỉ ghi điểm khi có khả năng rã đông hoặc làm nóng. OMW 2223 DS Microwave Digital với tốc độ và tiết kiệm năng lượng - cũng có vô số công thức để chuẩn bị những bữa ăn ngon và lành mạnh trong lò vi sóng. Ngoài ra, việc vệ sinh xoong, chảo phiền phức không còn cần thiết nữa." +
                                        "" +
                                        "Với màu bạc đơn giản, lò vi sóng phù hợp với mọi gian bếp. Dung tích của thiết bị là 20,0 l. Thiết bị có 15 mức công suất để bạn chuẩn bị tốt cho mọi tình huống. Lò vi sóng OK. có công suất 700 watt và có thể cung cấp tối đa 1000 watt." +
                                        "" +
                                        "Bàn xoay đảm bảo thức ăn được làm nóng đều. Chu vi bàn xoay là 255 mm. Với chức năng nướng tích hợp, các món ăn của bạn sẽ giòn tan. Lò nướng có thể sử dụng 1000 watt để nướng hoặc làm chín thực phẩm. Có màn hình hiển thị - bạn không cần phải mở cửa lò vi sóng để biết tình trạng sưởi ấm." +
                                        "" +
                                        "Lò vi sóng OK. có hiển thị thời gian. Bạn có thể sử dụng bộ hẹn giờ để cài đặt thời gian nấu các món ăn đã chuẩn bị chính xác khi bạn cần. Rã đông thứ gì đó nhanh chóng và dễ dàng - OMW 2223 DS có chức năng rã đông thực tế cho việc này." +
                                        "" +
                                        "Tóm lại: Nếu bạn coi trọng việc chuẩn bị bữa ăn nhanh chóng, không phức tạp, thì gói tổng thể này là OK. một lựa chọn tốt.","OK"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "GRUNDIG GFBM 23420 XC",27293160, R.drawable.m31,"72L","Bếp từ GRUNDIG GFBM 23420 XC với bếp gốm thủy tinh phù hợp với tất cả các đầu bếp khó tính!" +
                                        "" +
                                        "Thiết bị đứng trên sàn đặc biệt linh hoạt khi bố trí nhà bếp. Ngoài ra, thiết bị còn mang lại những giá trị tốt về hiệu suất năng lượng và thuộc loại A. Bếp điện dễ làm sạch và an toàn hơn nhiều so với bếp gas. Bạn có thể dễ dàng chế biến các món bánh ngon hoặc nướng trong lò. Thể tích của khoang nướng là 72 lít." +
                                        "" +
                                        "Một tính năng tuyệt vời khác là chức năng nướng. Bất kể thời tiết nướng thịt như thế nào, bạn có thể nướng thịt cho đến khi thịt giòn - trong nhà bếp của riêng bạn. Ngoài ra, chức năng không khí nóng cung cấp nhiều tùy chọn và ưu điểm hơn. Hệ thống sưởi nhanh hơn và kết quả đồng đều hơn chỉ là một vài trong số đó. Việc làm nóng trước lò thường không còn cần thiết với chương trình này vì nhiệt phát triển rất nhanh. Máy cũng được trang bị chức năng rã đông. Thực phẩm đông lạnh được đưa đến nhiệt độ chế biến nhẹ nhàng nhưng nhanh chóng. Giống như mọi lò nướng hiện tại, bếp độc lập này cũng có chức năng gia nhiệt trên và dưới - loại gia nhiệt phổ biến cho nhiều gợi ý công thức. Không khí tuần hoàn là sự bổ sung cổ điển cho nhiệt trên và dưới cổ điển; nó rút ngắn thời gian nướng cho nhiều món ăn vì nhiệt được phân bổ đều trong bên trong. Chỉ báo nhiệt dư tích hợp là một hệ thống kiểm soát tuyệt vời. Nó cho thấy liệu có còn nguy cơ bị bỏng hay không. Các nút điều khiển có thể thu vào không chỉ hấp dẫn về mặt hình ảnh mà còn ngăn ngừa rủi ro do vô tình thay đổi cài đặt. Tính năng an toàn cho trẻ em được tích hợp đảm bảo an ninh hơn. Điều này ngăn ngừa các tai nạn có thể phát sinh do tiếp xúc không chủ ý. Việc kiểm soát thời gian của thiết bị giúp việc chuẩn bị món ăn của bạn trở nên dễ dàng hơn. Tất nhiên, cũng có một bộ đếm thời gian thực tế. Một màn hình hiển thị thời gian cũng đã được tích hợp. Ánh sáng bên trong tốt giúp bạn luôn để mắt đến mọi thứ." +
                                        "" +
                                        "Bạn sẽ không muốn bỏ lỡ thiết bị này trong nhà bếp của mình: GFBM 23420 XC của GRUNDIG!","GRUNDIG"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "GORENJE BLACK SET III",22434900, R.drawable.m32, "71L","Những ai thích nấu nướng nhiều sẽ đánh giá cao bộ nồi tích hợp GORENJE BLACK SET III!" +
                                        "" +
                                        "Với mức tiêu thụ thấp, thiết bị thuộc loại hiệu quả năng lượng A." +
                                        "" +
                                        "Lò có dung tích 71 lít. Cài đặt nổi tiếng nhất cho hầu hết các lò nướng là nhiệt trên và dưới, tất nhiên cũng có sẵn trong kiểu máy này và như một chức năng rất phổ biến đảm bảo rằng các món ăn của bạn luôn ngon. Với mức tiêu thụ năng lượng trung bình là 0,94 kWh trong chương trình tiêu chuẩn thông thường, thiết bị nhà bếp này nằm ở giữa lĩnh vực này so với các thiết bị khác cùng loại. Chức năng không khí nóng cung cấp nhiều lựa chọn hơn, nhờ đó nhiệt được phân bổ đều bên trong lò và do đó đảm bảo nhiệt độ cao hơn. Điều này có thể làm giảm thời gian nướng và nấu. Khi được sử dụng trong chương trình không khí nóng / tuần hoàn tiêu chuẩn, sản lượng là 0,81 kWh. Một tính năng tuyệt vời khác là chức năng nướng. Bất kể thời tiết nướng thịt như thế nào, bạn có thể nướng thịt cho đến khi thịt giòn - trong nhà bếp của riêng bạn. Làm nóng nhanh chóng đưa lò đến nhiệt độ thích hợp trong vòng vài phút. Bánh mì cuộn, bánh mì và những thứ tương tự có thể được đưa đến nhiệt độ nhanh chóng và dễ dàng nhờ chức năng rã đông." +
                                        "" +
                                        "Thiết bị cũng có một điều khiển nhiệt độ cơ học. Việc vệ sinh lò không khiến bạn hoàn toàn yên tâm mà còn vô cùng đơn giản bởi tính năng tự làm sạch bằng thủy phân. Có thể dễ dàng đẩy khay vào lò bằng phần mở rộng kính thiên văn. Là một tính năng bổ sung thiết thực, lò có làm mát bên trong. Một cửa kính đã được lắp đặt để đóng cửa lò. Bộ này cũng bao gồm một tấm nướng." +
                                        "" +
                                        "Có bếp điện để hâm nóng thức ăn. Bề mặt gốm thủy tinh bắt mắt và dễ lau chùi cũng đảm bảo hiệu quả năng lượng nhờ khả năng dẫn nhiệt tốt. Bạn có thể nấu và chiên trên tổng cộng bốn vùng nấu. Giữ bữa tiệc của bạn ấm áp trong khi khách đã ăn xong. Nếu bạn muốn thêm một phần, bạn có thể được phục vụ ấm tuyệt vời nếu bạn sử dụng 6 vùng làm ấm của thiết bị. Với công suất 2,2 kW, vùng nấu đầu tiên có giá trị bình thường. Bếp điện từ thứ hai có công suất 1,2 kW và do đó sử dụng tương đối ít điện. Vùng nấu thứ ba sử dụng công suất 2,4 kW để làm nóng thức ăn. Chỉ với 1,2 kW, mức tiêu thụ năng lượng của bếp điện thứ tư nằm trong giới hạn hợp lý, không gây gánh nặng cho hóa đơn tiền điện của bạn. Đèn báo nhiệt dư tích hợp là một hệ thống kiểm soát tuyệt vời cho biết liệu có còn nguy cơ bỏng hay không." +
                                        "" +
                                        "Nếu bạn không thích việc các nút điều khiển liên tục bắt mắt, bạn sẽ thích nguyên lý hoạt động của bộ nồi này. Các nút chuyển đổi chỉ đơn giản là chìm vào thiết bị sau khi sử dụng. Ngoài ra, chỉ cần nhìn thoáng qua là có thể thấy rõ tấm nào đang được sử dụng. Thời gian cũng có thể được đọc. Một tính năng thiết thực khác là bộ hẹn giờ tích hợp. Bếp chạy trong hoạt động điện lưới bình thường." +
                                        "" +
                                        "Bạn sẽ không gặp phải sai lầm với BLACK SET III (BC737E28BG + ECD634X) từ GORENJE!","GOREN"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BOSCH HND679LS65, Einbauherdset ",60371760, R.drawable.m33 ,"71 L","Bếp điện từ FlexInduction NXX675CB1E: đặt nhiều nồi hoặc dụng cụ nấu lớn ở bất kỳ đâu trên vùng nấu linh hoạt." +
                                        "Vùng cảm ứng linh hoạt: Sự kết nối linh hoạt giữa các vùng nấu để tạo thành một bề mặt liên tục cho các nồi nhỏ và các phụ kiện đặc biệt lớn" +
                                        "Cảm ứng: nấu nhanh, chính xác, dễ dàng làm sạch và tiêu thụ năng lượng thấp." +
                                        "ComfortProfil: thiết kế sang trọng, thanh lịch với một đường cắt khía ở mặt trước và dải kim loại ở bên cạnh." +
                                        "PowerBoost: Thời gian đun sôi ngắn hơn với công suất tăng lên đến 50%." +
                                        "Hẹn giờ với chức năng tắt: tắt vùng nấu đã chọn sau thời gian mong muốn.","BOSCH"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BOSCH HBD 415 RS 61 EOX5, Backofenset",35792820, R.drawable.m34 ,"71 L","Lò tích hợp với không khí nóng 3D: bạn có thể đạt được kết quả nướng và nướng hoàn hảo ở ba cấp độ cùng một lúc." +
                                        "Không khí nóng 3D: kết quả hoàn hảo nhờ phân bổ nhiệt tối ưu lên đến 3 cấp độ cùng một lúc." +
                                        "Hoạt động với màn hình LED đỏ: thao tác dễ dàng nhờ truy cập trực tiếp vào các chức năng thời gian." +
                                        "GranitEmail: nội thất dễ lau chùi nhờ bề mặt nhẵn và không có cạnh sắc.","BOSCH"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "AEG KOMBI 3025, Einbauherdset ",28729320, R.drawable.m35,"72 L","HE604062XB bếp tích hợp" +
                                        "Tính linh hoạt tuyệt vời khi nấu nướng nhờ vào vùng nướng và hai vòng tròn" +
                                        "" +
                                        "Đèn báo nhiệt dư được bố trí ở bên","AEG"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BOSCH HBG675BS1 Einbauherd/Backofen",34750380, R.drawable.m36 ,"71 L","Lò nướng tích hợp: đây là cách bạn đạt được kết quả nướng và nướng hoàn hảo." +
                                        "Không khí nóng 4D: kết quả hoàn hảo nhờ phân phối nhiệt tối ưu - bất kể mức độ." +
                                        "AutoPilot 10: mọi món ăn đều hoàn hảo nhờ 10 chương trình tự động cài sẵn." +
                                        "Hoạt động với màn hình TFT: hoạt động dễ dàng nhờ vòng điều hành với văn bản và ký hiệu đơn giản" +
                                        "Nhiệt phân: không tốn công làm sạch nhờ tính năng tự làm sạch tự động. Các thiết bị có ngăn kéo cũng được chống nhiệt phân." +
                                        "Hiệu quả năng lượng loại A +: cho kết quả nướng tốt nhất với mức tiêu thụ thấp hơn.","BOSCH" , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BAUKNECHT HEKO BLACK 700 H, Herdset",27999000, R.drawable.m37,"65 L ","Bếp nấu sẵn Bauknecht thủy phân (65 lít)" +
                                        "MỤC 4" +
                                        "" +
                                        "Lò quạt với 8 chức năng" +
                                        "Chức năng làm sạch thủy phân" +
                                        "Thể tích không gian nấu 65 lít" +
                                        "Các nút điều khiển có thể thu vào","BAUKNECHT"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "MIELE H 2267-1B Einbaubackofen",32985780, R.drawable.m38 ,"76 L","H 2267-1 B ACTIVE - lò nướng có hẹn giờ, bộ chạy mở rộng đầy đủ PerfectClean và FlexiClip" +
                                        "Điểm nổi bật:" +
                                        "" +
                                        "Màn hình 7 đoạn với nút bật tắt có thể thu vào - EasyControl - các chế độ hoạt động và cài đặt được chọn bằng cách sử dụng nút bật tắt có thể thu vào. Việc hiển thị thông tin trạng thái như B. Cài đặt thời gian được thực hiện trên màn hình 7 đoạn." +
                                        "Đặc biệt dễ vệ sinh - Thiết bị PerfectClean - Làm sạch lại nhanh chóng: Vì không có thức ăn nào có thể bám dính hoặc đóng cặn nên việc vệ sinh cực kỳ dễ dàng." +
                                        "Nhiều không gian và linh hoạt trên 5 cấp độ - Thể tích không gian nấu 76 l - Việc phân chia không gian nấu ăn một cách chu đáo giúp bạn sử dụng hiệu quả." +
                                        "Dễ dàng cầm nắm - Phần mở rộng đầy đủ FlexiClip - Công thái học và tiện lợi: Phần mở rộng đầy đủ cho phép bạn lấy thực phẩm của mình trong lò một cách thoải mái và an toàn." +
                                        "Bảo vệ chống bỏng - mát phía trước - bảo vệ chống bỏng: thiết bị vẫn tương đối mát - ngay cả khi ở bên ngoài cửa.","MIELE"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "NEFF BCC3622 - B3CCE2AN0 Einbaubackofen",28999620, R.drawable.m39,"71 L ","Lò tích hợp với CircoTherm © - để nướng, quay và nấu đồng thời ở 3 cấp độ." +
                                        "Hide® - cửa lò có thể thu vào hoàn toàn." +
                                        "CircoTherm® - hệ thống khí nóng Neff tuyệt vời để nướng, rang và nấu đồng thời ở 3 cấp độ" +
                                        "EasyClean® - chất hỗ trợ làm sạch đặc biệt này cho phép làm sạch bên trong lò một cách đơn giản, tiết kiệm năng lượng." +
                                        "Một lựa chọn thân thiện với môi trường để làm sạch nhanh chóng ở giữa.","NEFF"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "SIEMENS HB634GBS1 Backofen",31200270, R.drawable.m40,"71 L","ĐIỂM NỔI BẬT CỦA Lò nướng HB634GBS1" +
                                        "Màn hình TFT: dễ đọc từ mọi góc độ." +
                                        "softMove: nhẹ nhàng đóng mở cửa lò." +
                                        "Không khí nóng 4D: sự lựa chọn linh hoạt của mức kệ." +
                                        "ecoClean Plus: làm sạch dễ dàng hơn nhờ lớp phủ đặc biệt.","SIEMENS"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "AEG BEB355020M Einbaubackofen",23812920, R.drawable.m41 ,"71 L " ,"Nấu ăn thống nhất ở tất cả các khu vực" +
                                        "Với lò nướng này, việc sử dụng năng lượng hiệu quả đồng nghĩa với việc nấu nướng hiệu quả cùng một lúc. Hệ thống khí nóng với bộ phận làm nóng vòng đảm bảo khí nóng luân chuyển đều khắp không gian nấu nướng. Lò làm nóng nhanh hơn và nhiệt độ nấu có thể giảm đến 20%, tiết kiệm cả thời gian và năng lượng." +
                                        "" +
                                        "Nấu nhiều hơn cùng một lúc mà vẫn đạt được kết quả hoàn hảo." +
                                        "Không khí nóng với bộ phận làm nóng vòng trong lò nướng của chúng tôi đảm bảo rằng các món ăn của bạn được nấu chín đều và với tối đa ba khay. Điều này mang lại sự phát triển đầy đủ hương vị trong toàn bộ khu vực nấu ăn. Mỗi phần ăn chắc chắn sẽ hoàn hảo như phần trước.","AEG"  , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "NEFF D65IHM1S0, Dunstabzugshaube",26891280, R.drawable.m42 ,"590x499mm","Máy hút mùi thiết kế với mặt trước nghiêng và hiệu suất mạnh mẽ, mang lại khoảng không khi nấu nướng." +
                                        "-TouchControl: nhờ giao diện người dùng mượt mà, đặc biệt dễ dàng điều khiển chỉ bằng một ngón tay.","NEFF"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PKM S20-90 AWTY, Dunstabzugshaube",7307790, R.drawable.m43 ,"895x385mm","Với máy hút mùi PKM S20-90 AWTY, hơi và mùi khó chịu không có cơ hội!" +
                                        "" +
                                        "Với mũ trùm đầu, bạn có thể tự do di chuyển lý tưởng khi nấu nướng, vì thiết kế đặc trưng của nó giúp bạn không bị va đập vào đầu. Sản phẩm được làm bằng kim loại, luôn rất dễ lau chùi và phù hợp với mọi môi trường. Do được bảo quản tốt, thiết bị này thuộc loại hiệu suất năng lượng A. Mức tiêu thụ điện năng trung bình thấp là 37,6 kWh mỗi năm. Đèn LED trên mô hình cung cấp ánh sáng và chiếu sáng tốt khu vực làm việc. Chúng cũng rất kinh tế và có tuổi thọ rất cao. Do cách làm việc tối ưu của chúng, các bóng đèn được xếp vào loại hiệu suất chiếu sáng A. Bạn có thể đặt ba tốc độ quạt để hút. Khi hoạt động đầy đủ, máy bơm khí xả hoạt động với công suất 616 watt. Bởi vì một công suất khổng lồ 616 watt được sử dụng trong chế độ tuần hoàn, các hạt chất béo được chiết xuất hoàn toàn. Hệ thống lọc chất lượng cao (hút cạnh) được sử dụng để tách các hạt và mùi ra khỏi không khí. Bộ lọc mỡ bằng nhôm có thể được làm sạch rất dễ dàng. Đặc biệt thiết thực: sản phẩm có thể được điều khiển thuận tiện bằng cách chạm vào nó bằng ngón tay của bạn. Vì vậy, bạn không cần phải tìm điều khiển từ xa để thực hiện cài đặt." +
                                        "" +
                                        "Lò sưởi gây ấn tượng với màu trắng cổ điển. Cánh đảo gió ngược bịt kín ống thoát khí để không khí bên ngoài bị ép vào bếp. Động cơ đạt công suất tối đa 200 watt. Điều này có nghĩa là sản phẩm chính xác ở mức trung bình của các sản phẩm thay thế tương đương. Với tốc độ lưu thông không khí 616,0 m³ / h, máy hút mùi này chắc chắn đáp ứng các yêu cầu hàng ngày." +
                                        "" +
                                        "Bạn sẽ không muốn làm gì nếu không có sản phẩm tuyệt vời này trong nhà bếp của mình!","PKM"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BAUKNECHT BAKO PIN 2000, Backofenset",35792820, R.drawable.m44 ,"71 L ","Lò nướng tích hợp BAR2 KP8V2 IN với nhiệt phân (71 l)" +
                                        "Lò có quạt với 10 chức năng" +
                                        "Hệ thống làm sạch nhiệt phân với 2 cấp độ" +
                                        "Cấp nguồn không khí nóng - phân phối đều khí nóng ngay từ đầu" +
                                        "Maxi Cooking - chức năng tối ưu hóa để nấu những miếng thịt lớn","BAUKNECKT"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PKM S2-90 ABTZ, Dunstabzugshaube",7963650, R.drawable.m45 ,"900x389mm","Với máy hút mùi PKM S2-90 ABTZ bạn mang công nghệ tuyệt vời và thiết kế hấp dẫn vào gian bếp!" +
                                        "" +
                                        "Với mũ trùm đầu, bạn có thể tự do di chuyển lý tưởng khi nấu nướng, vì thiết kế đặc trưng của nó giúp bạn không bị va đập vào đầu. Vì tính kinh tế của nó, thiết bị được xếp vào loại hiệu suất năng lượng A. Trung bình chỉ khoảng 41 kWh điện được sử dụng. Đèn LED được lắp đặt làm hệ thống chiếu sáng, cung cấp ánh sáng cho bề mặt làm việc của bạn và ánh sáng rất tiết kiệm và bền lâu. Với hiệu suất chiếu sáng loại A, đèn là một trong những loại đèn tốt nhất trên thị trường. Bởi vì công suất khổng lồ 637 watt được sử dụng để hút khí thải, các hạt chất béo và mùi không có cơ hội. Các chất cặn bẩn trên bề mặt làm việc và bếp của bạn đã là dĩ vãng, vì bộ lọc dầu mỡ (kim loại) làm sạch không khí một cách đáng tin cậy. Thực tế: làm sạch bộ lọc mỡ bằng nhôm rất dễ dàng. Sản phẩm có thể được vận hành một cách trực quan và thuận tiện bằng cách chạm vào nó bằng ngón tay của bạn." +
                                        "" +
                                        "Với màu đen cổ điển, lò sưởi kết hợp hài hòa với nhà bếp của bạn. Nếu bạn có mùi hoặc hơi nước quá nồng, bạn sẽ đặc biệt đánh giá cao mức độ chuyên sâu. Nó hút cực mạnh và đảm bảo khí hậu trong nhà tốt ngay lập tức. Cánh đảo gió ngược bịt kín ống thoát khí để không khí bên ngoài bị ép vào bếp. Công suất động cơ tối đa là 165 watt cũng rất thuyết phục. So với các dòng máy khác thì sản phẩm này chính xác là ở mức trung bình." +
                                        "" +
                                        "Sản phẩm tuyệt vời này chấm dứt mùi hôi!","PKM"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PANASONIC NN-GT 47 KMGPG Mikrowelle",9246300, R.drawable.m46,"1000 watt","Đặc tính nổi bật" +
                                        "Cảm biến Genius - nấu ăn điều khiển bằng cảm biến để chuẩn bị chính xác hoàn toàn tự động" +
                                        "Nấu kết hợp - sử dụng đồng thời hai nguồn nhiệt - cho kết quả nhanh chóng và tối ưu" +
                                        "Cảm biến Reheat - hâm nóng món ăn - kết quả tối ưu chỉ bằng một nút nhấn" +
                                        "Công nghệ biến tần - tỏa nhiệt nhẹ nhàng hơn đến 30%, nhanh hơn và tiết kiệm năng lượng hơn" +
                                        "Chức năng khởi động nhanh - cài đặt thời gian nấu chỉ bằng một nút bấm trong các bước 30 giây" +
                                        "Chức năng cộng thời gian - chỉ cần tăng thời gian nấu trong quá trình nấu theo thời gian mong muốn","PANASONIC"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "STEBA KB 28 Minibackofen",6255660, R.drawable.m47 ,"","Lò nướng mini STEBA KB 28 có thể cung cấp một giải pháp thay thế tiết kiệm không gian cho lò nướng." +
                                        "" +
                                        "Thiết bị đứng độc lập bổ sung cho thiết bị nhà bếp của bạn một cách trang nhã. Thể tích của khoang nướng là 28 lít. Lò nướng mini cũng có thể thuyết phục với các tính năng sau: 3 mức giá, nội thất chống dính, bánh nướng cho 2 con gà và vỏ thép không gỉ." +
                                        "" +
                                        "Chức năng nướng đặc biệt thiết thực. Với điều này, bạn có thể nướng thịt hầm, nướng thịt và nhiều hơn nữa ngay lập tức. Với chức năng nhiệt trên / dưới, điều không thể thiếu hiện nay, hầu hết các loại thực phẩm đều có thể được chế biến ngon trong lò. Chức năng lưu thông không khí cũng có nhiều ưu điểm. Vì quạt phân phối nhiệt đều bên trong nên thực phẩm có thể được nấu chín nhẹ nhàng ở nhiệt độ thấp hơn. Với bộ hẹn giờ tích hợp, bạn có một chức năng tiện lợi khác." +
                                        "" +
                                        "Chất lượng tốt nhất về mọi mặt, tốt nhất là bạn nên thuyết phục bản thân về sản phẩm STEBA này!","STEBA"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "SIEMENS EQ521KA00 (EA645GN17+HE517ABS0)",33329520, R.drawable.m48 ,"71 L ","Bếp tích hợp, EEK A, thể tích không gian nấu 71 lít" +
                                        "- 7 kiểu gia nhiệt - cookControl10: chương trình tự động cho kết quả tốt nhất - gia nhiệt nhanh: nhanh hơn đến nhiệt độ nướng mong muốn - men granit bóng: lớp phủ chất lượng cao giúp dễ dàng vệ sinh." +
                                        "Hob:" +
                                        "- Không gian cho chảo, nồi và lò nướng lớn nhờ vùng nấu hai vòng 21 cm và vùng rang có thể chuyển đổi.","SIEMENS"   , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "SIEMENS PQ521DB0ZM, Einbauherdset",58613280, R.drawable.m49,"71 L ","Rút ngắn thời gian nấu nhờ tính năng làm nóng nhanh, coolStart và activeClean®." +
                                        "(HE578BBS1)" +
                                        "" +
                                        "cookControl30: Các chương trình tự động mang lại kết quả tốt nhất." +
                                        "" +
                                        "Làm nóng nhanh: nhanh hơn đến nhiệt độ nướng mong muốn; coolStart: hâm nóng trước không còn cần thiết cho các bữa ăn đông lạnh." +
                                        "" +
                                        "Không khí nóng 3D: cho kết quả nướng tối ưu trên tối đa 3 khay nướng nhờ phân phối nhiệt sáng tạo." +
                                        "" +
                                        "activeClean: tự động làm sạch / nhiệt phân để làm sạch dễ dàng.","SIEMENS"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "AEG HK 654850 XB Glaskeramikkochfeld",15343860, R.drawable.m50,"576mm","Sử dụng nhiệt dư an toàn và hiệu quả" +
                                        "Bộ điều khiển OptiHeat ghi lại lượng nhiệt dư còn lại cho từng vùng sưởi và bạn có thể sử dụng nhiệt này một cách an toàn và hiệu quả hơn." +
                                        "" +
                                        "Kiểm soát vĩnh viễn bếp của bạn" +
                                        "Các yếu tố điều khiển đặc biệt của bếp này cung cấp cho bạn khả năng kiểm soát tức thì nhạy bén đối với các mức gia nhiệt. Bạn chỉ cần di chuyển ngón tay của mình đến cài đặt chính xác và bếp sẽ phản ứng tương ứng.","AEG"    , "Đức"));


                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi TEFAL A702A8\n",2982480, R.drawable.m51,"7 chiếc\n","Bên ngoài: thép không gỉ được đánh bóng cao. Hoàn thiện cẩn thận cho dụng cụ nấu bếp bền, sáng bóng và hấp dẫn","TEFAL\n" ,  "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi TEFAL P25307 Secure 5\n",2796840, R.drawable.m52,"\n","Với nồi áp suất Tefal, bạn nấu ăn một cách tiết kiệm năng lượng và bảo vệ tối ưu các chất dinh dưỡng. An toàn hoàn hảo nhờ 5 thiết bị an toàn từ Tefal: 3 thiết bị an toàn bảo vệ chống quá áp: van an toàn, van áp suất, phớt an toàn - 2 thiết bị an toàn đóng / mở: khóa an toàn, nắp không mở được dưới áp suất.","TEFAL\n",   "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi TEFAL P25443 Secure 5\n",4483920, R.drawable.m53, "","Với nồi áp suất Tefal, bạn nấu ăn một cách tiết kiệm năng lượng và bảo vệ tối ưu các chất dinh dưỡng. An toàn hoàn hảo nhờ 5 thiết bị an toàn từ Tefal: 3 thiết bị an toàn bảo vệ chống quá áp: van an toàn, van áp suất, phớt an toàn - 2 thiết bị an toàn đóng / mở: khóa an toàn, nắp không mở được dưới áp suất.","TEFAL\n" , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi WMF 07.7404.6380 \n",8523120, R.drawable.m54,"","Cho dù dành cho người mới bắt đầu hay đam mê nấu nướng: bộ nồi WMF 07.7404.6380 Quality One là lựa chọn tuyệt vời cho mọi gian bếp. Với bộ sản phẩm bạn có thể chế biến nhiều món ăn phong phú. Có thể là nước sốt, khoai tây luộc hoặc risotto, bộ này phù hợp với mọi thứ.\n" +
                                        "\n" +
                                        "Bát đĩa được làm bằng thép không gỉ nên không chỉ chống trầy xước, chịu nhiệt cực tốt mà còn giữ nhiệt tốt.\n" +
                                        "\n" +
                                        "Bộ nồi 07.7404.6380 Quality One từ WMF - trợ thủ nhà bếp không thể thiếu!","WMF" ,  "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi M&K BY ELO 99414\n",1043460, R.drawable.m55,"","Những ai thích tự tay vào bếp và thử các công thức khác nhau chắc chắn sẽ không sai lầm với chiếc nồi ninh nhừ M&K BY ELO 99414!\n" +
                                        "\n" +
                                        "Sự lựa chọn rơi vào thép không gỉ, loại thép này ghi điểm với khả năng chịu nhiệt cao và giữ nhiệt tốt. Ngoài ra, vật liệu có khả năng chống xước và không mùi, không vị. 160 mm là đường kính của bài báo. Với dung tích 1.50 lít, bình nấu có thể đáp ứng mọi yêu cầu.\n" +
                                        "\n" +
                                        "Nồi ninh từ M&K BY ELO 99414 hoàn thiện mọi gian bếp.","M&K\n",   "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi M&K BY ELO 99616\n",1142910, R.drawable.m56,  "Với nồi mì Ý và măng tây M&K BY ELO 99616, mì ống và rau củ được nấu chín hoàn hảo. Bạn cũng có thể chuẩn bị nước dùng, súp hoặc mứt trong một cái chảo lớn.\n" +
                                        "\n" +
                                        "Dụng cụ nấu ăn bằng thép không gỉ không chỉ trông sang trọng mà nó còn đặc biệt chống xước và chịu nhiệt. Hầu như tất cả các món ăn đều có thể được chế biến bằng sành sứ như vậy, đó là lý do tại sao nó cũng hoàn hảo cho người mới bắt đầu hoặc những người đầu bếp không thường xuyên. Với màu bạc bạn đang làm mọi thứ ngay trong bếp. Ngoài ra, bài báo có đường kính 160 mm. Bình nấu có dung tích 3,9 l nên phù hợp với hầu hết mọi mục đích.\n" +
                                        "\n" +
                                        "Nồi cho nhà bếp của bạn: nồi mì Ý và măng tây 99616 từ M&K BY ELO." ,"","M&K","Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "ELO 53928 Bombee Flötenkessel Edelstahl\n",1391280, R.drawable.m57, "","Với ấm siêu tốc ELO 53928 Bombee, ấm đun nước của bạn sẽ được đặt trong bóng râm. Ấm siêu tốc không chỉ vượt trội về mặt hình thức so với các loại ấm thông thường, với loại bếp và chất liệu phù hợp, ấm đun nước còn có thể làm nóng nước nhanh hơn và tốn ít năng lượng hơn. Ngoài những tác dụng này, ấm đun nước còn cung cấp cho bạn nhiều không gian hơn trên bề mặt làm việc và cho phép bạn xác định nhiệt độ nước tốt hơn.\n" +
                                        "\n" +
                                        "Bộ đồ ăn bằng thép không gỉ không chỉ rất bền và chịu nhiệt, mà còn đặc biệt linh hoạt. Hầu như bất kỳ món ăn nào cũng có thể được chế biến trong đó. Chất liệu này còn ghi điểm nhờ khả năng giữ nhiệt tốt. Ngoài ra, bài báo có đường kính 200 mm. Với dung tích 2,2 lít, nồi nấu hoàn hảo cho việc sử dụng bếp hàng ngày.\n" +
                                        "\n" +
                                        "Bạn nấu bằng gốm, điện, gas hay cảm ứng? Sau đó, mô hình là hoàn hảo cho bếp của bạn.\n" +
                                        "\n" +
                                        "Đặt mọi ấm đun nước trong bóng râm: Ấm siêu tốc 53928 Bombee của ELO!","ELO\n",  "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi TEFAL P 2530738 Secure 5 Neo\n",3226260, R.drawable.m58,  "","Cơ sở: cơ sở cảm ứng mạnh mẽ để phân phối nhiệt đều","TEFAL\n" ,"Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "  Nồi SILIT 21.0925.2900 Achat\n",4216170, R.drawable.m59,  "4 chiếc\n","Những người thích nấu ăn thường xuyên cần các loại nồi khác nhau, mặc dù một vài hình dạng cơ bản đã chứng tỏ họ là thiết bị tiêu chuẩn. Silit kết hợp những thứ này với các kích cỡ khác nhau thành một sự lựa chọn đa dạng, theo đó thịt hoặc nồi quay không nên thiếu trong bất kỳ bộ nào. Tất cả mọi thứ cần nhiều chất lỏng để nấu đều phù hợp với nồi hầm, vì mép của nó đặc biệt cao, không có gì văng ra ngoài khi nướng thịt và nó cũng chấp nhận mì ống, khoai tây và rau mà không có vấn đề gì. Hầm là sự kết hợp giữa chảo và cao, vành cao hơn chảo nên hầu như không có mỡ bắn ra ngoài khi rán. Đồng thời, mép thấp hơn so với thành của nồi thịt để chất lỏng bay hơi nhanh hơn và bạn dễ dàng trở thịt / rau củ. Bộ nồi Silit có thể được bổ sung bất cứ lúc nào với các nồi riêng lẻ từ dòng dụng cụ nấu.","SLIT\n", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Nồi hấp WMF 716836040\n",1822740, R.drawable.m60, "","Với tủ hấp rau củ, rau củ được chế biến nhanh chóng, dễ dàng và ít dầu mỡ nhất. Ngược lại với nấu chín, hấp cũng giúp giữ lại các chất dinh dưỡng quan trọng. Những chiếc nồi inox nhỏ có đường kính nhỏ rất lý tưởng cho hộ gia đình một người, cho những khẩu phần ăn nhỏ hoặc để hâm nóng thức ăn thừa. Chậu được làm bằng Cromargan® dễ chăm sóc và có đế vạn năng TransTherm® phù hợp với mọi loại bếp. Nồi hấp rau và nồi mì có một miếng chèn lỗ.","WMF\n",  "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,"chảo TEFAL B31405 Only Cook Bratpfanne\n",596190,R.drawable.m61,  "","Dòng sản phẩm chảo Tefal Only Cook phù hợp với mọi loại bếp ngoại trừ bếp từ và mang đến cho người mới bắt đầu thao tác dễ dàng và nhiều ưu điểm. Những chiếc chảo này kết hợp tất cả những ưu điểm của chảo Tefal với mức giá phải chăng và dễ dàng sử dụng hàng ngày!","TEFAL\n" , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,"chảo TEFAL B31406 Only Cook\n",596190,  R.drawable.m62 , "","Dòng sản phẩm chảo Tefal Only Cook phù hợp với mọi loại bếp ngoại trừ bếp từ và mang đến cho người mới bắt đầu thao tác dễ dàng và nhiều ưu điểm. Những chiếc chảo này kết hợp tất cả những ưu điểm của chảo Tefal với mức giá phải chăng và dễ dàng sử dụng hàng ngày!","TEFAL \n"      , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,"chảo TEFAL B31408 Only Cook\n",745110,  R.drawable.m63  , "","Dòng chảo Tefal Only Cook phù hợp với mọi loại bếp ngoại trừ bếp từ và mang đến cho người mới bắt đầu việc sử dụng dễ dàng và nhiều ưu điểm. Những chiếc chảo này kết hợp tất cả các ưu điểm của chảo Tefal với mức giá phải chăng và dễ sử dụng hàng ngày!\n" +
                                        "Thermo-Spot® tích hợp hiển thị nhiệt độ tối ưu để bắt đầu rang. Thermo-Spot® tích hợp trong chảo hiển thị khi chảo đã đạt đến nhiệt độ chiên lý tưởng để thêm thực phẩm vào chiên. Đế Durabase đảm bảo phân phối nhiệt nhanh và đều. Lớp chống dính Powerglide đảm bảo không còn thức ăn dính vào chảo. Nhờ có thêm tay cầm tiện lợi, chảo CHỈ NẤU nằm trên tay một cách đặc biệt thoải mái. Đế chảo với công nghệ DURABASE tạo sự phân bổ nhiệt nhanh và đều. Chảo ONLY COOK phù hợp với mọi loại bếp, trừ bếp từ. Hình dạng rộng để dễ dàng rót.","TEFAL \n"    , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,  "chảo TEFAL B31404 Only Cook\n",546210,R.drawable.m64, "", "Dòng sản phẩm chảo Tefal Only Cook phù hợp với mọi loại bếp ngoại trừ bếp từ và mang đến cho người mới bắt đầu thao tác dễ dàng và nhiều ưu điểm. Những chiếc chảo này kết hợp tất cả những ưu điểm của chảo Tefal với mức giá phải chăng và dễ dàng sử dụng hàng ngày!","TEFAL \n"     , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "chảo TEFAL A16806 Logics Bratpfanne\n", 808860,R.drawable.m65, "","Đế Durabase giúp phân phối nhiệt nhanh và đều. THERMO-SPOT®: Chỉ báo nhiệt độ tích hợp! Nó hiển thị khi chảo đã đạt đến nhiệt độ chiên lý tưởng. Với tay cầm bằng nhựa chịu nhiệt tiện dụng, an toàn với lò nướng lên đến 175 ° C. Thích hợp với mọi nguồn nhiệt, ngoại trừ cảm ứng.", "TEFAL \n"       , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "chảo TEFAL C69406 Chef's Delight \n",1492260, R.drawable.m66 , "","Chảo tương thích cảm ứng Tefal's Chef's Delight: Cho món ngon mỗi ngày! Nhờ lớp phủ Tefal Titanium Pro độc quyền, loạt chảo phủ lớp chống dính mạnh mẽ này có tuổi thọ cao hơn tới hai lần. (So \u200B\u200Bvới Tefal Titanium Force niêm phong)","TEFAL \n"       , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,"chảo M&K BY ELO 78203 Pfannen-Set\n",1406580,R.drawable.m67,"","Do có đế bằng sắt từ nên bộ chảo này cũng phù hợp với bếp từ. Hơn nữa, bài viết có khả năng chịu nhiệt, do đó bạn hầu như không bị hạn chế trong việc chuẩn bị bữa ăn của mình. Tất nhiên, chảo cũng có tay cầm. Dụng cụ nấu ăn an toàn với máy rửa chén, vì vậy không có hiện tượng cọ rửa khó chịu sau khi nấu.\n" +
                                        "\n" +
                                        "Với bộ chảo 78203 của M&K BY ELO, một chiếc chảo hoàn hảo thực sự sẽ tiến vào tủ bếp của bạn!","M&K\n"        , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,"chảo TEFAL B31402 Only Cook Bratpfanne\n",496740,R.drawable.m68, "","Dòng sản phẩm chảo Tefal Only Cook phù hợp với mọi loại bếp ngoại trừ bếp từ và mang đến cho người mới bắt đầu thao tác dễ dàng và nhiều ưu điểm. Những chiếc chảo này kết hợp tất cả những ưu điểm của chảo Tefal với mức giá phải chăng và dễ dàng sử dụng hàng ngày!","TEFAL \n"       , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "chảo WMF 07.4161.9990 3-tlg. Bratpfanne\n",4096830,R.drawable.m69,"","Với chiếc chảo này bạn cũng có thể nấu trên bếp từ. Tất nhiên, bài viết cũng có khả năng chịu nhiệt, vì vậy bạn có thể tiếp xúc với nhiệt độ rất cao mà không bị giảm chất lượng.\n" +
                                        "\n" +
                                        "Một chiếc chảo tốt không nên thiếu trong bất kỳ căn bếp nào! Chính vì vậy bạn nên bổ sung cho thiết bị bếp của mình chiếc chảo rán 07.4161.9990 3 món. từ WMF!","WMF\n"        , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5,"chảo TEFAL E44006 Talent Induction Bratpfanne\n",2003790,R.drawable.m70,  "","Bạn có thích tự do sáng tạo trong nhà bếp và chế biến các món ăn đa dạng? Vậy thì trong tủ bếp nhà bạn không nên thiếu chiếc chảo cảm ứng TEFAL E44006!","TEFAL \n"      , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "LEIFHEIT 3146 nghiền tỏi\n",645660,R.drawable.m71, "", "Knobi King từ Leifheit là phải cho tất cả những người hâm mộ tỏi. Công cụ trợ giúp nhà bếp mới hoạt động nhanh chóng, dễ dàng và mang lại kết quả như thể các ngón chân đã được cắt hạt lựu chính xác bằng tay. Bởi vì Vua Knobi không nghiền nát các ngón chân, mà là chia chúng ra. Một lần nhấn của thiết bị tạo hình là đủ và tép tỏi được cắt thành các khối nhỏ bằng một tấm đục lỗ chính xác. Sau khi sử dụng thành công, thiết bị tiện dụng đi vào máy rửa bát.", "LEIFHEIT\n"       , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "LEIFHEIT 23241 Salatschleuder\n",1378020, R.drawable.m72 ,  "", "Thanh lịch, kỹ lưỡng và linh hoạt: Cơ chế kéo cáp hiệu quả với khả năng xoay phải trái làm khô salad đặc biệt kỹ lưỡng và nhẹ nhàng. Bát thép không gỉ trang nhã phù hợp trên bất kỳ bàn nào như một bát phục vụ. Với bộ trợ rót tích hợp trong nắp và đế chống trượt làm bằng silicone.",     "LEIFHEIT\n"     , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Cân SOEHNLE 66220 Cooking Star\n",745110,     R.drawable.m73  ,    "", "- Cân nhà bếp kỹ thuật số chính xác 0,1 g, tải trọng 500 g",     "SOEHNLE\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "GEFU 13460 Spirelli Spiralschneider\n",802740,     R.drawable.m74  ,    "", "Với máy cắt xoắn ốc, bạn có thể tạo ra những dải julienne vô tận từ cà rốt, củ cải, dưa chuột, bí xanh và nhiều loại rau củ khác. Các đường xoắn ốc 2 x 3 mm hoặc 3,5 x 5 mm lý tưởng để tạo ra các món rau xào hoặc mì ống và để trang trí các đĩa phục vụ. Sự kết hợp giữa nhựa cao cấp và thép không gỉ giúp dễ dàng vệ sinh dưới vòi nước.",     "GEFU \n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Cân KOENIC KKS 3220\n",699210,     R.drawable.m75  ,    "", "Về mặt trực quan, quy mô nhà bếp dựa trên thiết kế màu trắng và đỏ, do đó phát triển một hiệu ứng rất độc đáo trong nhà bếp của bạn. Cân nhà bếp mang lại hiệu suất trung bình về khả năng chịu tải: chúng có thể dễ dàng nâng lên đến 5 kg. Thiết bị có màn hình LCD tối ưu dễ đọc. Vì vậy, bạn luôn để ý xem liệu bạn đã đạt đến số tiền cần thiết hay chưa.",     "KOENIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "TEFAL 8535.31 mở nắp hộp\n",917490,     R.drawable.m76  ,    "", "Với màu trắng, bộ đồ hộp hoàn toàn phù hợp với các loại đồ dùng nhà bếp. Nó được làm bằng nhựa nên tốt và cầm trên tay. Xét cho cùng, công cụ trợ giúp nhà bếp có công suất tối đa là 20 watt.",     "TEFAL\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "WMF 18.7134.6030 cắt pizza\n",745110,     R.drawable.m77  ,    "", "Nếu bạn thích nấu ăn thì nên xem qua máy cắt bánh pizza WMF 18.7134.6030 Profi Plus. Nó sẽ bổ sung cho thiết bị của bạn một cách hoàn hảo!\n" +
                                        "\n" +
                                        "Ngoài ra, sản phẩm còn gây ấn tượng với màu bạc tinh tế.\n" +
                                        "\n" +
                                        "Máy cắt pizza 18.7134.6030 Profi Plus của WMF sẽ trở thành viên ngọc quý trong thiết bị nhà bếp của bạn!",     "WMF\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "GEFU 13560 Apfelschäler\n",929730,     R.drawable.m78  ,    "", "Dụng cụ gọt táo",     "GEFU \n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "WMF 605972000 Messbecher\n",688500,     R.drawable.m79  ,    "", "Bình chia độ",     "WMF\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "WMF 06.6737.4500 tay xoay tự động\n",3193620,     R.drawable.m80  ,    "", "Sự kết hợp giữa gỗ Cromargan® và gỗ ngựa vằn cao cấp mang đến cho nhà máy điện Ceramill vẻ ngoài chất lượng cao. Mài trở thành trò chơi của trẻ chỉ với một nút nhấn. Máy xay được trang bị cối xay sứ Ceramill chất lượng cao. Động cơ điện mạnh mẽ cho phép công suất mài cao. Mức độ xay cũng có thể được điều chỉnh liên tục (từ thô đến mịn). Nắp hương thơm thiết thực ngăn mùi hương trên bàn.",     "WMF\n"         , "Đức"));



                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Dao ECHTWERK W-DM-0377-2\n",4981680,     R.drawable.m81  ,    "", "Bộ dao làm bếp chất lượng cao\n" +
                                        "\n" +
                                        "Bộ dao Damascus 6 chiếc. Bao gồm Khối dao từ làm bằng gỗ",     "ECHTWERK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Dao ECHTWERK EW-DM-0322-1\n",5456490,     R.drawable.m82  ,    "", "Bộ dao chất lượng cao với hiệu suất cắt tuyệt vời\n" +
                                        "-1 khối dao từ, 5 dao- Thép Damascus được làm 67 lớp- HRC 56-58 ° Rockwell cứng",     "ECHTWERK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Mài dao GRAEF Piccolo\n",492660,     R.drawable.m83  ,    "", "Với máy mài dao mini bằng tay Piccolo, bạn có thể mài dao bất cứ lúc nào - dù đang ở nhà trong bếp hay đang di chuyển khi cắm trại hoặc săn bắn.",     "GRAFF\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Dao ECHTWERK EW-MB-0130 Square Messerblock\n",831810,     R.drawable.m84  ,    "", "Khối dao đa năng ECHTWERK \"hình vuông\" có chèn lông - màu trắng",     "ECHTWERK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Dao CLATRONIC EM 3702 Elektromesser\n",1042950,     R.drawable.m85  ,    "", "Dao điện\n" +
                                        "Động cơ 120 watt mạnh mẽ, hoạt động ít tiếng ồn và độ rung thấp\n" +
                                        "Lưỡi dao đa năng bằng thép không gỉ có răng cưa đặc biệt để cắt chính xác mọi loại thực phẩm, kể cả thực phẩm đông lạnh (chống gỉ / an toàn cho máy rửa chén)\n" +
                                        "Nút kích hoạt cho lưỡi dao, công tắc an toàn\n" +
                                        "Chèn bằng thép không gỉ, bao gồm hộp lưu trữ",     "CLATRONIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Mài dao STEBA KS 1 Messerschärfer\n",2727480,     R.drawable.m86  ,    "", "Máy mài dao STEBA KS 1\n" +
                                        "Máy mài dao điện dùng cho tất cả các loại dao (kim loại, gốm)",     "STEBA\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Đựng dao WMF 1880509999 Messerblock, unbestückt\n",2013480,     R.drawable.m87  ,    "", "Nấu ăn thậm chí còn thú vị hơn với các phụ kiện phù hợp. Chính xác vì lý do này, bạn nên xem xét kỹ hơn khối dao, không có WMF 1880509999!\n" +
                                        "\n" +
                                        "Hãy thử khối dao 1880509999 từ WMF cho chính bạn!",     "WMF\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Đá mài dao WMF 1875136030\n",2087430,     R.drawable.m88  ,    "", "Những người thích nấu nướng sẽ đặc biệt đánh giá cao đá mài WMF 360/1000 1875136030. Một tài sản thực sự cho thiết bị nhà bếp của bạn!\n" +
                                        "\n" +
                                        "Không nên thiếu whetstone grit 360/1000 1875136030 từ WMF trong nhà bếp của bạn!",     "WMF\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Mài dao WMF 1880276270\n",983280,     R.drawable.m89  ,    "", "Dụng cụ mài dao WMF 1880276270 là một bổ sung tuyệt vời cho thiết bị nhà bếp của bạn. Nấu ăn thậm chí còn thú vị hơn!\n" +
                                        "\n" +
                                        "Làm việc trong bếp sẽ dễ chịu hơn nhiều với máy mài dao WMF 1880276270!",     "WMF\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Mài dao WMF 18.7437.6030\n",1518270,     R.drawable.m90  ,    "", "Phụ kiện truyền cảm hứng: máy mài dao WMF 18.7437.6030.\n" +
                                        "\n" +
                                        "Tay nghề tốt được thể hiện ngay lập tức từ lưỡi dao mạnh mẽ. Một số vật liệu được sử dụng ở đây ghi điểm với đặc tính cắt tốt của chúng (Cromargan®: thép không gỉ, nhựa, chống gỉ 18/10).",     "WMF\n"         , "Đức"));




                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc OK. OHD 103 B \n",504390,     R.drawable.m91  ,    "", "Với tay cầm gấp gọn, bạn cũng có thể mang theo máy sấy tóc trong những chuyến du lịch hoặc đựng trong một chiếc túi nhỏ để tập thể thao. Cáp dài 1,8 m.\n" +
                                        "\n" +
                                        "Tóc như mới từ thợ làm tóc - với máy sấy tóc từ OK. Điều ước này không còn là dĩ vãng.",     "OK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tócPHILIPS HP8230/00\n",786420,     R.drawable.m92  ,    "", "Không khí ấm làm cho tóc trở nên thô ráp và nhạy cảm, trong khi không khí lạnh đóng lại cấu trúc, mang lại độ bóng cho tóc và đảm bảo rằng tóc vẫn giữ được nếp. Vì lý do này, mô hình cũng được trang bị cài đặt lạnh. Chiều dài cáp là 1,8 m.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc DYSON 323916-01\n",20349000,     R.drawable.m93  ,    "", "Nhiệt độ được đo 40 lần / giây bằng cảm biến và tự động điều chỉnh nhiệt độ xuống nếu cần để duy trì độ sáng bóng tự nhiên. Phiên bản quà tặng bao gồm ngoài tiêu chuẩn",     "DYSON\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc OK. OHD 4211B \n",705330,     R.drawable.m94  ,    "", "Không khí ấm làm cho tóc trở nên thô ráp và nhạy cảm, trong khi không khí lạnh đóng lại cấu trúc, mang lại độ bóng cho tóc và đảm bảo rằng tóc vẫn giữ được nếp. Vì lý do này, mô hình cũng được trang bị cài đặt lạnh. Cáp dài 1,8 m.",     "OK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc PHILIPS HP8232/00\n",1591200,     R.drawable.m95  ,    "", "Bảo vệ tóc của bạn trong khi tận hưởng kết quả làm khô nhanh chóng. Các cài đặt tốc độ và nhiệt độ khác nhau đảm bảo kết quả sấy khô mong muốn. Tính năng ThermoProtect để sấy khô nhanh chóng ở nhiệt độ ổn định, nhẹ nhàng cũng như chức năng ion hóa với tác dụng chống xoăn cứng cho tóc bóng mượt.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc PHILIPS HP 8280/00 \n",4275840,     R.drawable.m96  ,    "", "Chức năng ion hóa ngăn ngừa tĩnh điện cho mái tóc thẳng và bóng mượt\n" +
                                        "Các ion tích điện âm loại bỏ tĩnh điện, nuôi dưỡng tóc và làm mịn lớp biểu bì bên ngoài cho tóc bóng mượt. Kết quả là tóc thẳng, bóng, khỏe.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc PHILIPS HP8232/20\n",1690650,     R.drawable.m97  ,    "", "Không khí ấm làm cho tóc trở nên thô ráp và nhạy cảm, trong khi không khí lạnh đóng lại cấu trúc, mang lại độ bóng cho tóc và đảm bảo rằng tóc vẫn giữ được nếp. Vì lý do này, mô hình cũng được trang bị cài đặt lạnh. Chiều dài của cáp là 1,8 m.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc BRAUN Satin Hair 1 HD\n",670650,     R.drawable.m98  ,    "", "Máy sấy tóc Braun Satin Hair Style & Go: nhiều điện áp: sử dụng an toàn trên toàn thế giới.",     "BRAUN\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc REMINGTON D 5220\n",1370370,     R.drawable.m99  ,    "", "Máy tạo ion làm giảm điện tích tĩnh, cho phép tóc khô nhanh hơn và mang lại độ bóng sáng không gì sánh được. Một bộ khuếch tán và vòi phun tạo kiểu được bao gồm cho mọi nhu cầu tạo kiểu.",     "REMINGTON\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy sấy tóc REMINGTON D3190\n",1094460,     R.drawable.m100  ,    "", "Máy sấy tóc D3190 công suất 2200 watt tích hợp bộ tạo ion có 3 cấp độ sưởi và 2 cấp độ quạt riêng biệt cũng như cấp độ làm mát. Máy tạo ion làm giảm điện tích tĩnh, cho phép tóc khô nhanh hơn và mang lại độ bóng sáng không gì sánh được. Vòi tạo kiểu và bộ khuếch tán 9 mm hoàn thành gói hoàn hảo để làm khô tóc.",     "REMINGTON\n"         , "Đức"));


                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê KOENIC KCM 2220 B\n",1750830,     R.drawable.m101  ,    "Màu đen\n", "từ KOENIC, bạn sẽ có được một chiếc máy pha cà phê thanh lịch vượt thời gian bằng thép không gỉ / đen.\n" +
                                        "Nó có công suất 900 watt và công suất 10-12 cốc. Với sự trợ giúp của bình giữ nhiệt inox, dung tích 1.25 lít bạn có thể dễ dàng giữ ấm trong thời gian dài.",     "KOENIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê OK. OCM 105 W\n",753780,     R.drawable.m102  ,    "Màu đen\n", "Mô hình màu trắng là ấn tượng ngay từ cái nhìn đầu tiên. Thiết kế mà bạn thích! Bạn có thể chuẩn bị tối đa 10 tách cà phê cho mỗi quá trình pha. Máy pha cà phê có thể tạo ra công suất lên đến 870 watt. Tiềm năng này rất quan trọng vì nó cho phép nước đạt đến nhiệt độ nấu bia lý tưởng. Bạn sẽ nhận thấy vị thơm hơn khi uống muộn nhất!",     "OK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê  PHILIPS HD7546/20\n",3054900,     R.drawable.m103  ,    "Màu đen\n", "Đối với cà phê nóng và mới pha\n" +
                                        "Máy pha cà phê này chuẩn bị cà phê nóng, mới và thơm ngon bất cứ khi nào bạn muốn một cách rất đơn giản và nhanh chóng",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê MELITTA AromaFresh \n",8691930,     R.drawable.m104  ,    "Màu đen\n", "Cà phê mới xay mang lại hương thơm cà phê tối đa: Hầu hết những người yêu cà phê có lẽ sẽ đồng ý về điều này.\n" +
                                        "\n" +
                                        "Không có gì ngạc nhiên khi Melitta® AromaFresh với máy xay tích hợp của nó đã nhanh chóng trở thành sản phẩm bán chạy nhất kể từ khi được giới thiệu. Một động lực để chúng tôi phát triển một biến thể bổ sung của máy pha cà phê phin đặc biệt này. Chúng tôi cũng đã cải thiện hơn nữa việc xử lý.",     "MELITTA\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê MELITTA 1021-01 AromaFresh\n",5927730,     R.drawable.m105  ,    "Màu đen\n", "Bảo vệ hoàn hảo cho máy pha cà phê AromaFresh của bạn.\n" +
                                        "Tẩy cặn thường xuyên là một phần của chương trình để bạn có thể sử dụng AromaFresh lâu dài và có được hương vị cà phê ngon nhất từ \u200B\u200Bmáy.",     "MELITTA\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê ROWENTA CT 3818 Milano\n",3004410,     R.drawable.m106  ,    "Màu đen\n", "Với thiết kế màu đen, nó kết hợp hoàn hảo với đồ đạc trong nhà bếp của bạn. Bạn có thể chuẩn bị tối đa 12 cốc với thiết bị cho mỗi quá trình pha. Nhiệt độ của nước khi bột cà phê chảy qua rất quan trọng đối với chất lượng của thức uống thành phẩm. Với công suất lên đến 850 watt, máy pha cà phê có thể pha một cách hoàn hảo và do đó đảm bảo hương thơm đậm đà cho cà phê của bạn.",     "ROWENTA\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê PHILIPS HD5408/20\n",4969950,     R.drawable.m107  ,    "Màu đen\n", "Bạn thích thức dậy vào buổi sáng để thưởng thức cà phê từ HD5408 / 20 Café Gourmet của PHILIPS!\n" +
                                        "\n" +
                                        "Ngay cái nhìn đầu tiên, máy đã ghi điểm với thiết kế hai màu đen và bạc. Sau khi đổ đầy, thiết bị có thể chuẩn bị đến 10 cốc. Nếu bạn để bình trong máy, máy pha cà phê này sẽ tiếp tục hâm nóng đồ uống cho bạn với chức năng giữ ấm. Điều này có nghĩa là cà phê không bị mất đi vị ngon.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê KRUPS F.309.08\n",1490730,     R.drawable.m108  ,    "Màu đen\n", "Với thiết kế màu đen, nó kết hợp hoàn hảo với căn bếp của bạn. Những người yêu thích cà phê đều biết: Hiệu suất của thiết bị rất quan trọng đối với thức uống thành phẩm. Với công suất lên đến 1050 watt, máy pha cà phê có thể đảm bảo nhiệt độ pha tối ưu cho nước. Không có gì cản trở được hương thơm nồng nàn!",     "KRUPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê BOSCH TKA6A043\n",1839060,     R.drawable.m109  ,    "Màu đen\n", "Với thiết kế màu đen, nó kết hợp hoàn hảo với căn bếp của bạn.",     "BOSCH\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy pha cà phê MELITTA 100801\n",2718810,     R.drawable.m110  ,    "Màu đen\n", "Thưởng thức tách cà phê phin thành phẩm của bạn ngay khi thức dậy\n" +
                                        "Thời gian bắt đầu pha có thể được đặt riêng với Bộ hẹn giờ Melitta® Optima. Mẫu bình thủy tinh này còn gây ấn tượng với thiết kế nhỏ gọn và các chức năng bổ sung hữu ích.",     "MELITTA\n"         , "Đức"));


                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà KOENIC KRVC 2320\n",8055960,     R.drawable.m111  ,    "Màu đen\n", "Một bình chứa nước và một phần đính kèm sợi nhỏ được đặt ở phần phía dưới phía sau của robot hút bụi lau nhà Nó được cung cấp nước tự động và có thể được làm sạch ướt cùng một lúc.\n" +
                                        "\n" +
                                        "Do đó, robot phù hợp với mọi bề mặt.",     "KOENIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà ECOVACS DEEBOT \n",25449000,     R.drawable.m112  ,    "Màu đen\n", "DEEBOT OZMO 950 kết hợp khả năng di chuyển của robot hút bụi thông thường với sức hút của máy hút bụi không dây thông thường. * Nó có ba cấp độ hút để bạn có thể đặt công suất hút phù hợp cho mọi loại bụi bẩn và sàn nhà. Nhờ động cơ mạnh mẽ và thời lượng pin dài, nó cũng có thể hút bụi và lau sạch các khu vực sinh hoạt lớn chỉ trong một lần. Công nghệ điều hướng được cải tiến không chỉ lập bản đồ và ghi lại căn phòng tốt hơn mà còn cho phép lưu nhiều tầng. **",     "ECOVACS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà ROBOROCK S6 MaxV\n",30549000,     R.drawable.m113  ,    "Màu đen\n", "Ai có cảm giác muốn hút bụi và lau ?! Ngoại trừ S6 MaxV, có lẽ không có. Sử dụng thời gian của bạn một cách khôn ngoan trong khi anh ấy làm việc nhà. Kinh nghiệm nhiều năm của Roborock được phản ánh trong S6 MaxV và đưa robot chân không lên một tầm cao mới. Với camera kép được hỗ trợ bởi trí thông minh nhân tạo, nó có thể di chuyển các vật thể bị bỏ lại phía sau hoặc vật nuôi mà không vi phạm quyền riêng tư của bạn. Ngay cả trong bóng tối nhờ hệ thống hình ảnh hồng ngoại của nó. Với lực hút tăng 25% so với người tiền nhiệm của nó và hệ thống lau được cải tiến, đây là sản phẩm tối ưu trong số các robot chân không.",     "ROBOROCK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà NEATO Robotics D7\n",18819000,     R.drawable.m114  ,    "Màu đen\n", "Làm sạch khu vực cho phép bạn tiếp cận các khu vực có vấn đề trong nhà của bạn thường xuyên hơn. Ví dụ: nếu bạn thiết lập một khu vực cho bàn ăn, robot của bạn sẽ lao đến nhặt tất cả các mẩu vụn tích tụ mỗi ngày.",     "NRATO\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà ROBOROCK S5 Max\n",22386450,     R.drawable.m115  ,    "Màu trắng\n", "Ở thế hệ mới của Roborock S5 Max, những điểm mạnh của người tiền nhiệm vẫn không ngừng được phát huy. Chức năng lau sáng tạo đã được phát triển hơn nữa và hiện được trang bị một bình chứa bổ sung lớn hơn và nguồn cung cấp nước có thể kiểm soát, hiện cho phép lau và / hoặc hút bụi trong một lần. 14 cảm biến cho phép ghi lại các phòng một cách chính xác và tạo các vùng xóa ảo, ví dụ: trong lĩnh vực thảm.",     "RRBOROCK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà IROBOT Roomba e5158\n",17748000,     R.drawable.m116  ,    "Màu đen\n", "Robot có hơn 30 năm kinh nghiệm trong lĩnh vực công nghệ và đổi mới robot và đã bán được hơn 30 triệu robot gia dụng trên toàn thế giới cho đến nay. Cho dù bạn chọn Roomba, Braava hay cả hai - sàn của bạn nhận được sự chăm sóc đặc biệt mà họ cần.",     "IROBOT\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà ZACO A6\n",9942450,     R.drawable.m117  ,    "Màu đen\n", "Khi tất cả các tầng đều sạch sẽ, ZACO A6 sẽ tự động quay trở lại trạm sạc. Cũng có thể gửi trực tiếp đến trạm sạc thông qua điều khiển từ xa. Sau đó, A6 sẽ tự động cập bến và sạc pin lithium-ion tuổi thọ cao cho lần sử dụng tiếp theo.",     "ZACO\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà ROWENTA RR6825\n",9639000,     R.drawable.m118  ,    "Màu đen\n", "Explorer 20 gây ấn tượng với chổi quét trung tâm cho khả năng hút bụi cao và thời gian chạy là 150 phút. Robot tự động lái xe trở lại trạm để sạc và sẵn sàng sau khi tối đa. Sẵn sàng hoạt động trở lại sau 6 giờ. 3 chế độ làm sạch đảm bảo làm sạch tối ưu. Cách nhanh chóng và dễ dàng để hút bụi. Điều khiển từ xa cho phép dễ dàng nhập lịch trình nhờ màn hình LCD.",     "ROWENTA\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà ECOVACS Deebot\n",11679000,     R.drawable.m119  ,    "Màu trắng\n", "DEEBOT 605 là người trợ giúp tiện dụng của bạn mà bạn có thể tự tin - nó sạc pin, dọn dẹp khi nào và ở đâu bạn yêu cầu, nó tránh chướng ngại vật, không va vào đồ đạc. Và công nghệ an toàn cầu thang của anh ấy thậm chí còn đảm bảo rằng anh ấy không bị ngã xuống cầu thang. Việc nhà chưa bao giờ dễ dàng đến thế!",     "ECOVACS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Robot dọn nhà IROBOT Roomba 606\n",10887480,     R.drawable.m120  ,    "Màu đen\n", "iRobot tạo ra sự khác biệt.\n" +
                                        "• iRobot có hơn 30 năm kinh nghiệm trong các công nghệ và cải tiến robot và đã bán được hơn 30 triệu robot gia dụng trên toàn thế giới cho đến nay. Cho dù bạn chọn Roomba, Braava hay cả hai - sàn của bạn nhận được sự chăm sóc đặc biệt mà họ cần.\n" +
                                        "\n" +
                                        "• Hệ thống làm sạch với 3 cấp độ giúp loại bỏ mọi thứ từ những hạt nhỏ nhất đến những mảnh bụi bẩn lớn hơn hoặc lông động vật.\n" +
                                        "\n" +
                                        "• Đầu lau tự động điều chỉnh tự động điều chỉnh độ cao của nó để bàn chải đôi cho các bề mặt khác nhau luôn tiếp xúc với các bề mặt sàn khác nhau và thảm được làm sạch hiệu quả như sàn cứng.",     "IROBOT\n"         , "Đức"));





                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện PHILIPS HX 6839/28\n",3892320,     R.drawable.m121  ,    "Màu trắng\n", "Bàn chải đánh răng điện âm HX6839 / 28 ProtectiveClean 4500 của Philips Sonicare giúp làm trắng răng đẹp hơn. Chế độ trắng loại bỏ sự đổi màu hiệu quả. Chế độ làm sạch sẽ loại bỏ triệt để các mảng bám trên răng. Nhờ có một cảm biến, nếu áp lực quá lớn sẽ phát ra tiếng động rung, do đó ngay cả những vùng nhạy cảm, mắc cài và răng giả cũng có thể được làm sạch mà không gặp bất kỳ vấn đề gì. Dòng chất lỏng động đảm bảo các khoảng trống sạch. Chức năng BrushSync nhắc bạn thay đổi tệp đính kèm vào thời điểm thích hợp. Khi đi du lịch, bàn chải đánh răng sonic có thể được cất giữ an toàn trong hộp đựng kèm theo.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện ORAL-B Vitality\n",1116390,     R.drawable.m122  ,    "Màu đen\n", "Bàn chải đánh răng điện có thể sạc lại Oral-B Vitality 100 CrossAction cung cấp khả năng làm sạch vượt trội so với bàn chải đánh răng thủ công thông thường và do đó là mẫu bàn chải đầu vào lý tưởng trong chăm sóc răng miệng bằng điện. Đầu bàn chải CrossAction chuyên nghiệp bao quanh từng chiếc răng với đầu lông nghiêng 16 độ, trong khi công nghệ làm sạch 2D loại bỏ mảng bám tốt hơn so với bàn chải thủ công thông thường thông qua các chuyển động dao động và xoay. Bộ đếm thời gian trong tay khoan giúp bạn đánh răng trong 2 phút theo khuyến nghị của nha sĩ.",     "ORAL-B\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện PHILIPS Sonicare\n",1979310,     R.drawable.m123  ,    "Màu trắng\n", "Giúp bạn tuân thủ các khuyến nghị về nha khoa: hẹn giờ hai phút đảm bảo tuân thủ thời gian làm sạch được khuyến nghị. Bộ đếm thời gian Quadpacer đảm bảo chải răng kỹ lưỡng.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện PHILIPS Sonicare HX\n",3145680,     R.drawable.m124  ,    "Màu trắng\n", "Bàn chải đánh răng điện sonic HX6807 / 51 ProtectiveClean 4300 của Philips Sonicare góp phần cải thiện sức khỏe răng miệng với đầy đủ các dòng sản phẩm của nó.\n" +
                                        "\n" +
                                        "Nếu áp lực quá lớn, một âm thanh rung động sẽ cảnh báo bạn về điều này.\n" +
                                        "\n" +
                                        "Điều này có nghĩa là các vùng nhạy cảm, niềng răng và răng giả cũng có thể được điều trị mà không gặp bất kỳ vấn đề gì.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện ORAL-B PRO\n",1518780,     R.drawable.m125  ,    "Màu trắng\n", "Với bộ hẹn giờ, Oral-B PRO 1 200 giúp bạn đánh răng chuyên nghiệp như nha sĩ trong 2 phút và thông báo cho bạn thay đổi vùng đánh răng sau mỗi 30 giây.",     "ORAL-B\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện ORAL-B PRO 2\n",3638340,     R.drawable.m126  ,    "Màu đen\n", "Bộ hẹn giờ tích hợp đảm bảo bạn đánh răng trong 2 phút theo khuyến nghị của nha sĩ và thông báo cho bạn sau mỗi 30 giây để thay đổi khu vực đánh răng. Đầu bàn chải tròn độc đáo giúp loại bỏ nhiều mảng bám hơn so với bàn chải đánh răng thủ công truyền thống, giúp nướu khỏe mạnh hơn và giúp nụ cười của bạn tươi sáng hơn bằng cách loại bỏ sự đổi màu trên bề mặt. Pin lithium-ion của Oral-B PRO 2 kéo dài hơn 2 tuần chỉ với một lần sạc đầy. Vì vậy, bạn không phải lo lắng về việc luôn có bộ sạc trong tay khi bạn đi nghỉ. Bàn chải đánh răng sạc điện Oral-B PRO 2 có thể sử dụng với các đầu bàn chải Oral-B sau: CrossAction, 3DWhite, Sensi Ultrathin, Sensitive, Precision Clean, Deep Cleansing, TriZone, Ortho Care.",     "ORAL-B\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện PHILIPS Sonicare AirFloss\n",4000440,     R.drawable.m127  ,    "Màu trắng\n", "Philips Sonicare AirFloss Ultra HX8438 / 01 với hai đầu phun đảm bảo cảm giác miệng sạch sâu. Với vòi phun hiệu suất cao và công nghệ vi giọt đã được chứng minh lâm sàng, nó cho phép làm sạch các kẽ răng và đường viền nướu một cách nhẹ nhàng và hiệu quả. Toàn bộ khoang miệng có thể được làm sạch chỉ trong 60 giây. Tùy thuộc vào sở thích của bạn, tia cực mạnh của AirFloss Ultras có thể được điều chỉnh riêng với tối đa ba lần xịt. Bể chứa trong ống ngậm có thể được đổ đầy nước hoặc nước súc miệng để chăm sóc kháng khuẩn.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện ORAL-B PRO 2 2000S\n",2581110,     R.drawable.m128  ,    "Màu đen\n", "Tính năng sản phẩm:\n" +
                                        "- Loại bỏ mảng bám nhiều hơn đến 100%: Đầu bàn chải tròn làm sạch tốt hơn và đảm bảo nướu răng khỏe mạnh hơn trong 30 ngày so với bàn chải đánh răng thủ công thông thường - Bảo vệ nướu răng của bạn: điều khiển áp suất làm giảm tốc độ khi chải răng với áp lực quá mạnh",     "ORAL-B\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện ORAL-B PRO 2 2000\n",2581110,     R.drawable.m129  ,    "Màu đen\n", "Bảo vệ nướu răng của bạn: điều khiển áp suất trực quan sẽ sáng lên và giảm tốc độ nếu bạn ấn quá nhiều.\n" +
                                        "Đầu bàn chải tròn giúp loại bỏ nhiều mảng bám hơn so với bàn chải đánh răng thủ công thông thường.\n" +
                                        "2 chế độ làm sạch: làm sạch hàng ngày và nhạy cảm.\n" +
                                        "Thời lượng pin hơn 2 tuần chỉ với một lần sạc đầy.\n" +
                                        "Luôn giữ đúng thời gian làm sạch là 2 phút với bộ hẹn giờ chuyên nghiệp.",     "ORAL-B\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Bàn chải điện PHILIPS SoniCare DiamondClean\n",10887480,     R.drawable.m130  ,    "Màu đen\n", "Nhờ kết nối ứng dụng, phản hồi về hành vi đánh răng được đưa ra trong thời gian thực với các mẹo được cá nhân hóa về cách chăm sóc nướu và răng.\n" +
                                        "Có 4 chương trình làm sạch và 3 cài đặt cường độ để bạn lựa chọn.\n" +
                                        "Tính năng phát hiện đầu bàn chải thông minh là thực tế, vì nó chọn chế độ làm sạch tối ưu khi đầu bàn chải được gắn vào.\n" +
                                        "Bàn chải đánh răng có thể được sạc trong kính sạc thời trang và một hộp đựng du lịch cũng được bao gồm.",     "PHILIPS\n"         , "Đức"));




                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BEURER 795.15 SFT 65 Fieberthermometer\n",1411170,     R.drawable.m131  ,    "", "Nhiệt kế lâm sàng BEURER 795.15 SFT 65 màu trắng là một trợ thủ nhỏ hữu ích cho tủ thuốc của bạn.\n" +
                                        "\n" +
                                        "Có thể dễ dàng xác định nhiệt độ cơ thể ở tai.\n" +
                                        "\n" +
                                        "Thiết bị này cung cấp sự chắc chắn trong trường hợp nhiệt độ cơ thể tăng lên: nhiệt kế lâm sàng 795.15 SFT 65 của BEURER.",     "SANITAS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "ANGELSOUNDS JPD 100 SM Fetal\n",2113440,     R.drawable.m132  ,    "", "Để nghe nhịp tim của con bạn, AngelSounds Mini có đầu ra âm thanh mà bạn có thể kết nối với tai nghe được cung cấp. Bạn có thể tùy chọn ghi lại âm thanh của em bé và phát nhịp tim cho những người thân yêu của bạn hoặc gửi chúng cho người thân và bạn bè. Có thể ghi âm với bất kỳ thiết bị thương mại nào có chức năng ghi âm và ổ cắm có kết nối giắc cắm 3,5 mm, ví dụ: Máy tính, máy ghi âm MP3 và máy tính xách tay.",     "ANGELSOUNDS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BRAUN PRT 1000 Fieberthermometer\n",496230,     R.drawable.m133  ,    "", "Độ chính xác chuyên nghiệp, dễ sử dụng\n" +
                                        "- Đo nhiệt độ chính xác chỉ trong 10 giây - Lần đo cuối cùng được lưu - Âm báo hiệu khi kết thúc quá trình đo",     "BRAUN\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PHILIPS SCD 733/26\n",5969550,     R.drawable.m134  ,    "", "Màn hình em bé Philips Avent DECT SCD 733/26 tạo kết nối an toàn với em bé.\n" +
                                        "Một ưu điểm đặc biệt của thiết bị là kết nối an toàn và không bị nhiễu với con bạn, do đó việc truyền tải dữ liệu là tuyệt đối riêng tư.\n" +
                                        "Nhờ công nghệ DECT, âm thanh đặc biệt rõ ràng và khác biệt.\n" +
                                        "Việc chiếu bầu trời đầy sao là một điểm nhấn rất đặc biệt, vì cách này giúp bé có thể tĩnh tâm trước khi ngủ.\n" +
                                        "Màn hình trẻ em có phạm vi lên đến 330 mét ngoài trời.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PHILIPS SCD 713/26 AUDIO DECT Babyphone\n",4113150,     R.drawable.m135  ,    "", "Màn hình trẻ em Philips Avent SCD713 / 26 cung cấp kết nối liên tục an toàn với trẻ nhờ công nghệ DECT. Tiếng ồn được truyền đi với âm thanh trong trẻo và hoàn toàn không bị nhiễu. Bằng cách này, các nhu cầu của em bé luôn có thể được ghi lại nhanh chóng và bầu không khí riêng tư vẫn được bảo vệ. Ngoài các sự kiện âm thanh, nhiệt độ phòng cũng có thể được theo dõi bằng màn hình. Với phạm vi lên đến 330 mét, thiết bị có thể dễ dàng được sử dụng trong các phòng ở xa hơn. Một chiếc đèn ngủ êm dịu và những bài hát ru hay giúp con cháu bình tĩnh và nhanh chóng chìm vào giấc ngủ.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BEURER 952.60 BY 33 Babyphone\n",1655970,     R.drawable.m136  ,    "", "Màn hình kỹ thuật số cho em bé cho những giờ yên tĩnh và những đêm an toàn",     "BRAUN\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "4MOMS mamaRoo 4 Babywippe Schwarz\n",13009590,     R.drawable.m137  ,    "", "Xe đẩy trẻ em công nghệ cao mamaRoo 4 từ 4moms mô phỏng các chuyển động quay và phù hợp cho bé yêu của bạn ngay từ những giờ đầu tiên nhờ bộ chèn có sẵn dành riêng cho trẻ sơ sinh.\n" +
                                        "Trên thiết bị, bạn có thể đặt năm chuyển động và tốc độ khác nhau (= 25 kết hợp) và chọn giữa bốn âm thanh tự nhiên nhẹ nhàng hoặc chỉ đơn giản là phát bản nhạc yêu thích của bạn qua kết nối MP3. Một điểm nổi bật cho tất cả những người đam mê công nghệ vì giờ đây bạn cũng có thể sử dụng Bluetooth để tạo chuyển động và âm thanh điều khiển bằng ứng dụng 4moms!",     "4MOMS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "BRAUN ThermoScan® 3 Fieberthermometer\n",1516740,     R.drawable.m138  ,    "", "Nhiệt kế hồng ngoại đo tai Braun ThermoScan® 3 nhỏ gọn cho cả gia đình\n" +
                                        "Đáng tin cậy và dễ sử dụng Với thiết kế mới và chỉ báo sốt âm thanh mới\n" +
                                        "Nhanh chóng và chính xác",     "BRAUN"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "PHILIPS SCD 503/26 AUDIO DECT Babyphone\n",2549490,     R.drawable.m139  ,    "", "Với màn hình trẻ em Philips Avent DECT SCD503 / 26, con yêu nhỏ của bạn cũng có thể được bảo vệ an toàn từ xa. Nhờ công nghệ DECT, tiếng ồn được truyền đi với âm thanh trong trẻo, nhờ đó có thể xác định nhu cầu của bé một cách nhanh chóng. Với sự hỗ trợ của các chỉ báo mức, các thay đổi âm thanh cũng có thể được ghi lại trên màn hình. Ngoài ra, thiết bị này hoàn toàn không bị nhiễu, do đó bầu không khí riêng tư được đảm bảo. Với phạm vi 330 mét, nó cũng có thể được sử dụng trong các phòng ở xa hơn mà không gặp bất kỳ trở ngại nào. Đèn ngủ êm dịu giúp con cháu được nghỉ ngơi.",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "ANSMANN Mobiles Nachtlicht Maus Nachtlicht\n",517650,     R.drawable.m140  ,    "", "Thiết kế màu cam khiến chiếc đèn trở nên bắt mắt tuyệt đối. Đèn cũng có độ quang thông tốt là 1,2 lumen. Quang thông cho biết một nguồn sáng phát ra bao nhiêu ánh sáng ở mỗi bên. Giá trị càng cao thì đèn phát ra càng nhiều ánh sáng trên một đơn vị thời gian.",     "ANSMANN\n"         , "Đức"));




                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay KOENIC KBL 350\n",1009800,     R.drawable.m141  ,    "", "Thiết kế tinh tế với màu trắng và xám rất phù hợp với phần còn lại của phạm vi nhà bếp của bạn. Thùng có thể chứa 0,6 lít, do đó vừa tiết kiệm không gian vừa cung cấp đủ không gian cho các nguyên liệu của bạn. Với công suất tối đa 350 watt, thiết bị không cần phải ẩn. Nhựa đã được sử dụng để làm vỏ máy.",     "KOENIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay KOENIC KHB 4421\n",1981860,     R.drawable.m142  ,    "", "Thiết kế tinh tế với màu đen và chrome rất phù hợp với phần còn lại của dòng sản phẩm nhà bếp của bạn. Thùng có dung tích vừa đủ (0,5 lít, 0,8 lít). Thiết bị có thể hoạt động đặc biệt mạnh mẽ với 1000 watt. Vỏ của thiết bị được làm bằng thép không gỉ và nhựa.",     "KOENIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay KOENIC KBL 713\n",2370480,     R.drawable.m143  ,    "", "Với thiết kế cổ điển bằng thép không gỉ, máy trộn đứng trông đẹp trong mọi gian bếp. Với dung tích 1.3 lít, bình chứa không quá to cũng không quá nhỏ. Với công suất tối đa 700 watt, thiết bị không cần phải ẩn. Vỏ được làm bằng thép không gỉ và do đó đặc biệt chắc chắn.",     "KOENIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay KOENIC KHM 3210 B\n",1310700,     R.drawable.m144  ,    "", "Từ lòng trắng trứng bông mịn đến bột men mạnh mẽ, nó đánh bại mọi thứ nhờ vào 5 cấp tốc độ, cộng với một tốc độ turbo và động cơ 450 watt mạnh mẽ. Với bề mặt cảm ứng mềm, bạn cũng có mọi thứ trong tầm kiểm soát.",     "KOENIC\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay OK. OCH 100\n",806820,     R.drawable.m145  ,    "", "Thiết kế màu trắng đầy phong cách nhấn mạnh rằng đây là một tác phẩm cổ điển. Bạn có thể sử dụng công suất cực đại mạnh mẽ là 260 watt để cắt hầu hết các loại thực phẩm. Một lượng lớn nguyên liệu có thể được đưa vào thiết bị vì dung tích là 0,6 l. Bát nhựa chắc chắn và có thể làm sạch dễ dàng. Bạn chỉ cần đặt thiết bị vào máy rửa bát để làm sạch.",     "OK\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay BOSCH MMBH6P6BDE\n",8664900,     R.drawable.m146  ,    "", "6 chương trình tự động cho sinh tố, đồ lắc, súp, nước sốt và kem, cùng với chương trình làm sạch\n" +
                                        "\n" +
                                        "Điều chỉnh tốc độ vô hạn và chức năng xung\n" +
                                        "\n" +
                                        "Các bộ phận an toàn cho máy rửa bát (nắp, tay đẩy)",     "BOSCH \n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay PHILIPS HR 2657/90\n",4026450,     R.drawable.m147  ,    "", "Thưởng thức các bữa ăn lành mạnh ở nhà hoặc khi đang di chuyển. Máy xay cầm tay ProMix Viva Collection mới với nhiều phụ kiện hỗ trợ bạn chuẩn bị nhiều món ăn nhẹ lành mạnh và sáng tạo thực phẩm",     "PHILIPS\n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay BOSCH MFQ3530\n",1490730,     R.drawable.m148  ,    "", "MUM4 - sản phẩm cổ điển đã được kiểm chứng trong số các máy nhà bếp - với chất lượng tuyệt vời của Bosch và với nhiều lựa chọn phụ kiện.",     "BOSCH \n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay ESGE 90750\n",11169000,     R.drawable.m149  ,    "", "Khi chuẩn bị thức ăn, bạn có thể chọn giữa hai mức chức năng. Bạn đã cảm thấy muốn tự tay mình cầm chiếc máy xay sinh tố chưa? Trục nhựa cho cảm giác rất tốt. Chiều dài của cáp là 1,5 m.",     "ESGE \n"         , "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, "Máy xay PHILIPS HR 3655/00\n",6137850,     R.drawable.m150  ,    "", "Máy xay sinh tố HR3655 / 00 thuộc Bộ sưu tập Philips Avance hỗ trợ cung cấp lượng vitamin lành mạnh hàng ngày. Nhờ động cơ mạnh mẽ 1400 watt, bạn có thể tạo ra những ly sinh tố thơm ngon ngay lập tức, giúp bạn dễ dàng ăn nhiều trái cây và rau quả cùng một lúc. Các mức tốc độ khác nhau có thể được điều chỉnh để phù hợp với độ đặc của thực phẩm. Có thể sản xuất tối đa 2 l trong một lần và bạn có thể sử dụng các chai uống kèm theo để chuẩn bị đồ uống khi di chuyển. Vì các bình chứa an toàn với máy rửa chén, nên việc vệ sinh cũng dễ dàng.",     "PHILIPS"         , "Đức"));


























                                //Hãng 6:
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 6, "Son dưỡng Dior Addict", 250000, R.drawable.sondior, "Hong", "Son dưỡng môi Dior Addict Lip là dòng son dưỡng đình đám, được đánh giá cao nhất cao nhất trong cộng đồng làm đẹp", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 7, "Power Youth Essence", 350000, R.drawable.kemskii, "50ml", " sử dụng sản phẩm tinh chất chống lão hóa RNA Power Youth Essence 50ml mỗi ngày để làn da luôn căng mịn tràn đầy khỏe khoắn và tươi trẻ", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 8, "BÁNH TRUNG THU NGUYỆT ÁNH", 160000, R.drawable.banhtrungthu, "600g", "Trung thu là Tết đoàn viên, cho cả nhà quây quần bên ấm trà thơm, thưởng thức những vị bánh Trung thu ngọt lành từ Tôm hùm sốt Hồng Kông, Gà quay sốt X.O, Hạt sen Macca và Khoai môn hạt óc chó", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry( 8, "BÁNH TRUNG THU BÍCH NGUYỆT", 160000, R.drawable.banhtrungthu2, "600g", "Hơn cả một món bánh truyền thống của dân tộc mùa lễ hội, những chiếc hộp mang tới vị bánh Trung thu: Ngũ nhân, Thập cẩm lạp xưởng, Hạt sen hạt chia, Sữa dừa chính là “bản giao hưởng đêm trăng”, là món ăn tinh thần cho cả gia đình trong đêm rằm tháng Tám", "Hit", "Đức"));
                                Log.v( "kiem tra insert sanpham" , "da insert ");

                            }
                        });
                    }
                })
                .build();

    }



    public abstract SanPhamDao sanPhamDao();

    public abstract GioHangDao gioHangDao();

    public abstract YeuThichDao yeuThichDao();
}
