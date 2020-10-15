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
/*
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(1, 0, "Táo Pink Lady", 150000,
                                R.drawable.taopinklady, "500g", "Táo Pink Lady New Zealand từ khi ra đời đã nhanh chóng chiếm được trái tim của người tiêu dùng, với hương vị tươi ngon, vỏ màu hồng đẹp tự nhiên, cùng nhiều thành phần dinh dưỡng bên trong, rất tốt cho sức khỏe.",
                                "Hit", "New Zealand"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(2, 0, "Nho xanh ", 80000, R.drawable.nhoxanh, "500g", "Hương vị thơm ngon, giòn ngọt tự nhiên. Cung cấp nhiều vitamin và khoáng chất tốt cho cơ thể",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(3, 0, "Socola Toffifee", 40000, R.drawable.socolatofiiee, "125g", "Socola Toffifee Kẹo bao phủ bên ngoài lớp caramen dẻo ngọt, lớp tiếp theo là socola và ở giữa là hạt hạnh nhân. Thưởng thức kẹo Toffifee đem đến cảm giác rất thú vị bởi sự kết hợp giữa vị ngọt của sữa, trứng gà, cafe, 1 ít bột vani cùng sữa tươi, dư chút vị đắng của socola và giòn bùi ẩn sâu bên trong cùng chiếc kẹo",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, 1, "Mercedes C200", 1300000000, R.drawable.merc, "Mau Trang", "Xe được nhớ đến bởi vẻ ngoài hài hòa giữa nét thể thao và lịch lãm, trang bị tiện nghi sang trọng, cao cấp đi kèm một mức giá vừa phải cho một sản phẩm tới từ thương hiệu lừng danh Mercedes Benz.",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, 2, "Bánh Sisi Mix Brand", 50000, R.drawable.banhsisi, "220g","Bánh kẹo Lumar Sisi Mix được làm từ Socola hảo hạn",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(6, 2, "Táo Mỹ", 180000, R.drawable.taomy, "300g", "Táo Mỹ mang vị ngọt tự nhiên, độ giòn hoàn hảo", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(7, 2, "Socola Ferrero", 80000, R.drawable.socolaferrero, "200g", "Ferrero Rocher được sản xuất tại Ý từ năm 1982 và nổi tiếng trên toàn thế giới về vị ngon & độ tinh xảo, từ chất lượng sản phẩm đến sự độc đáo về hình thức.",
                                "Hit", "Đức"));

                        Log.d(LOG_TAG, "insert data in database");
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(8, 2, "Bánh cá Fancy", 45000, R.drawable.banhfancy, "250g", "Bánh cá Fancy vàng ươm, thơm phức, ngon tuyệt cú mèo.",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(9, 2, "Socola Milka", 40000, R.drawable.socolamilka, "90g", "Với quy trình sản xuất hiện đại, nguyên liệu được chọn lựa kỹ lưỡng tại 8 quốc gia khác nhau, những thanh socola Milka mang trong mình thứ hương vị thơm ngon, tinh tế và hoàn toàn khác biệt.",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(10, 2, "Bánh Quy Leibniz", 20000, R.drawable.banhleibniz, "200g", "Bánh quy Leibniz được sản xuất trên dây chuyền công nghệ hiện đại, nhập khẩu trực tiếp từ Đức, hương vị thơm ngon giúp bổ sung năng lượng hàng ngày.",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(11, 2, "Socola Merci", 30000, R.drawable.socolamerci, "250g", "Kẹo socola Merci được làm từ bột ca cao hảo hạng nhất, nguyên liệu tốt nhất. Hương vị đậm nét đặc trưng, thiết kế bao bì bắt mắt, hấp dẫn. Merci có hương vị ngọt ngào pha lẫn chút đắng nhẹ nơi đầu lưỡi, chút béo ngậy của sữa và hương cacao, tất cả tạo nên một hương vị đặc biệt đầy tinh tế.",
                                "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(12, 2, "Socola Ritter Sport", 15000, R.drawable.socolarittersport, "200g", "Ritter Sport là sản phẩm nổi tiếng của Đức và được xuất khẩu đến hơn 90 nước trên thế giới. Hương vị không thể nhầm lẫn!\n", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(13, 3, "Nước Hoa Calvin Klein", 700000, R.drawable.nuochoack, "50ml", "Tuy sở hữu thiết kế đơn giản nhưng hương thơm ngọt ngào mà những sản phẩm này tạo ra luôn được đánh giá cao. Calvin Klein đã nhận nhiều giải thưởng danh giá dành cho nước hoa thông qua các sáng tạo của mình.", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(14, 3, "Nước hoa DG", 1500000, R.drawable.nuochoadg, "100ml", "Sự tinh tế hòa quyện giữa nét nam tính cuốn hút và đầy lịch lãm, The One for Men Eau de Parfum Intense mang đến hương thơm nồng nàn đột phá cho bộ sưu tập Dolce&Gabbana The One for Men", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(15, 3, "Nước Hoa HUGO BOSS", 1000000, R.drawable.nuochoahugo, "75ml", "Boss The Scent Men Là dòng nước hoa mang xu hướng nhẹ nhàng, bình yên nhưng quyết đoán mà nhãn hiệu nước hoa Hugo đã tạo nên. Đây là một hương thơm quen thuộc nhưng vẫn giữ được nét riêng biệt mà ít có dòng nước hoa nào làm được vậy.", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(16, 3, "Nước hoa Tom Ford", 2000000, R.drawable.nuochoatomford, "50ml", "Oud Wood-gỗ trầm hương không những trở thành một trong những tác phẩm cổ điển của riêng thương hiệu Tom Ford, mà có một bộ sưu tập mới với tên gọi “Oud Collection” đã được ra đời với các mùi hương gỗ trầm hương, gỗ đàn hương và hoắc hương giữ vai trò chủ đạo", "Hit", "Đức"));
                        ;
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(17, 4, "Laptop Dell XPS 17", 17000000, R.drawable.dellxps17, "2,1kg", "Điểm nổi bật nhất trên XPS 9700 chính là màn hình 17-inches InfinityEdge sắc nét, độ phân giải có tùy chọn lên tới 4K cảm ứng. Đây là một trong những chiếc laptop có tỉ lệ màn hình so với thân máy cao nhất, với viền màn hình chỉ dày 5mm giúp tối ưu hóa diện tích hiển thị", "Hit", "Đức"));
                        ;
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(18, 5, "Audi A3", 1100000000, R.drawable.audia3, "Trang", "Chiếc sedan hạng sang nhỏ nhất của Audi được phát triển từ nền tảng mới nhất của dòng xe Audi A3, được thiết kế phù hợp với những khách hàng trẻ tuổi, năng động sống tại các thành phố lớn", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(19, 6, "Son dưỡng Dior Addict", 250000, R.drawable.dior, "Hong", "Son dưỡng môi Dior Addict Lip là dòng son dưỡng đình đám, được đánh giá cao nhất cao nhất trong cộng đồng làm đẹp", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(20, 7, "Power Youth Essence", 350000, R.drawable.kemskii, "50ml", " sử dụng sản phẩm tinh chất chống lão hóa RNA Power Youth Essence 50ml mỗi ngày để làn da luôn căng mịn tràn đầy khỏe khoắn và tươi trẻ", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(21, 8, "BÁNH TRUNG THU NGUYỆT ÁNH", 160000, R.drawable.banhtrungthu, "600g", "Trung thu là Tết đoàn viên, cho cả nhà quây quần bên ấm trà thơm, thưởng thức những vị bánh Trung thu ngọt lành từ Tôm hùm sốt Hồng Kông, Gà quay sốt X.O, Hạt sen Macca và Khoai môn hạt óc chó", "Hit", "Đức"));
                        sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(22, 9, "BÁNH TRUNG THU BÍCH NGUYỆT", 160000, R.drawable.banhtrungthu2, "600g", "Hơn cả một món bánh truyền thống của dân tộc mùa lễ hội, những chiếc hộp mang tới vị bánh Trung thu: Ngũ nhân, Thập cẩm lạp xưởng, Hạt sen hạt chia, Sữa dừa chính là “bản giao hưởng đêm trăng”, là món ăn tinh thần cho cả gia đình trong đêm rằm tháng Tám", "Hit", "Đức"));

*/
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
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(1, 0, "Táo Pink Lady", 150000,
                                        R.drawable.taopinklady, "500g", "Táo Pink Lady New Zealand từ khi ra đời đã nhanh chóng chiếm được trái tim của người tiêu dùng, với hương vị tươi ngon, vỏ màu hồng đẹp tự nhiên, cùng nhiều thành phần dinh dưỡng bên trong, rất tốt cho sức khỏe.",
                                        "Hit", "New Zealand"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(2, 0, "Nho xanh ", 80000, R.drawable.nhoxanh, "500g", "Hương vị thơm ngon, giòn ngọt tự nhiên. Cung cấp nhiều vitamin và khoáng chất tốt cho cơ thể",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(3, 0, "Socola Toffifee", 40000, R.drawable.socolatofiiee, "125g", "Socola Toffifee Kẹo bao phủ bên ngoài lớp caramen dẻo ngọt, lớp tiếp theo là socola và ở giữa là hạt hạnh nhân. Thưởng thức kẹo Toffifee đem đến cảm giác rất thú vị bởi sự kết hợp giữa vị ngọt của sữa, trứng gà, cafe, 1 ít bột vani cùng sữa tươi, dư chút vị đắng của socola và giòn bùi ẩn sâu bên trong cùng chiếc kẹo",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(4, 1, "Mercedes C200", 1300000000, R.drawable.merc200, "Mau Trang", "Xe được nhớ đến bởi vẻ ngoài hài hòa giữa nét thể thao và lịch lãm, trang bị tiện nghi sang trọng, cao cấp đi kèm một mức giá vừa phải cho một sản phẩm tới từ thương hiệu lừng danh Mercedes Benz.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(5, 2, "Bánh Sisi Mix Brand", 50000, R.drawable.banhsisi, "220g","Bánh kẹo Lumar Sisi Mix được làm từ Socola hảo hạn",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(6, 2, "Táo Mỹ", 180000, R.drawable.taomy, "300g", "Táo Mỹ mang vị ngọt tự nhiên, độ giòn hoàn hảo", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(7, 2, "Socola Ferrero", 80000, R.drawable.socolaferrero, "200g", "Ferrero Rocher được sản xuất tại Ý từ năm 1982 và nổi tiếng trên toàn thế giới về vị ngon & độ tinh xảo, từ chất lượng sản phẩm đến sự độc đáo về hình thức.",
                                        "Hit", "Đức"));

                                Log.d(LOG_TAG, "insert data in database");
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(8, 2, "Bánh cá Fancy", 45000, R.drawable.banhfancy, "250g", "Bánh cá Fancy vàng ươm, thơm phức, ngon tuyệt cú mèo.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(9, 2, "Socola Milka", 40000, R.drawable.socolamilka, "90g", "Với quy trình sản xuất hiện đại, nguyên liệu được chọn lựa kỹ lưỡng tại 8 quốc gia khác nhau, những thanh socola Milka mang trong mình thứ hương vị thơm ngon, tinh tế và hoàn toàn khác biệt.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(10, 2, "Bánh Quy Leibniz", 20000, R.drawable.banhleibniz, "200g", "Bánh quy Leibniz được sản xuất trên dây chuyền công nghệ hiện đại, nhập khẩu trực tiếp từ Đức, hương vị thơm ngon giúp bổ sung năng lượng hàng ngày.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(11, 2, "Socola Merci", 30000, R.drawable.socolamerci, "250g", "Kẹo socola Merci được làm từ bột ca cao hảo hạng nhất, nguyên liệu tốt nhất. Hương vị đậm nét đặc trưng, thiết kế bao bì bắt mắt, hấp dẫn. Merci có hương vị ngọt ngào pha lẫn chút đắng nhẹ nơi đầu lưỡi, chút béo ngậy của sữa và hương cacao, tất cả tạo nên một hương vị đặc biệt đầy tinh tế.",
                                        "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(12, 2, "Socola Ritter Sport", 15000, R.drawable.socolarittersport, "200g", "Ritter Sport là sản phẩm nổi tiếng của Đức và được xuất khẩu đến hơn 90 nước trên thế giới. Hương vị không thể nhầm lẫn!\n", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(13, 3, "Nước Hoa Calvin Klein", 700000, R.drawable.nuochoack, "50ml", "Tuy sở hữu thiết kế đơn giản nhưng hương thơm ngọt ngào mà những sản phẩm này tạo ra luôn được đánh giá cao. Calvin Klein đã nhận nhiều giải thưởng danh giá dành cho nước hoa thông qua các sáng tạo của mình.", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(14, 3, "Nước hoa DG", 1500000, R.drawable.nuochoadg, "100ml", "Sự tinh tế hòa quyện giữa nét nam tính cuốn hút và đầy lịch lãm, The One for Men Eau de Parfum Intense mang đến hương thơm nồng nàn đột phá cho bộ sưu tập Dolce&Gabbana The One for Men", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(15, 3, "Nước Hoa HUGO BOSS", 1000000, R.drawable.nuochoahugo, "75ml", "Boss The Scent Men Là dòng nước hoa mang xu hướng nhẹ nhàng, bình yên nhưng quyết đoán mà nhãn hiệu nước hoa Hugo đã tạo nên. Đây là một hương thơm quen thuộc nhưng vẫn giữ được nét riêng biệt mà ít có dòng nước hoa nào làm được vậy.", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(16, 3, "Nước hoa Tom Ford", 2000000, R.drawable.nuochoatomford, "50ml", "Oud Wood-gỗ trầm hương không những trở thành một trong những tác phẩm cổ điển của riêng thương hiệu Tom Ford, mà có một bộ sưu tập mới với tên gọi “Oud Collection” đã được ra đời với các mùi hương gỗ trầm hương, gỗ đàn hương và hoắc hương giữ vai trò chủ đạo", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(17, 4, "Laptop Dell XPS 17", 17000000, R.drawable.dellxps17, "2,1kg", "Điểm nổi bật nhất trên XPS 9700 chính là màn hình 17-inches InfinityEdge sắc nét, độ phân giải có tùy chọn lên tới 4K cảm ứng. Đây là một trong những chiếc laptop có tỉ lệ màn hình so với thân máy cao nhất, với viền màn hình chỉ dày 5mm giúp tối ưu hóa diện tích hiển thị", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(18, 5, "Audi A3", 1100000000, R.drawable.audia3, "Trang", "Chiếc sedan hạng sang nhỏ nhất của Audi được phát triển từ nền tảng mới nhất của dòng xe Audi A3, được thiết kế phù hợp với những khách hàng trẻ tuổi, năng động sống tại các thành phố lớn", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(19, 6, "Son dưỡng Dior Addict", 250000, R.drawable.dior, "Hong", "Son dưỡng môi Dior Addict Lip là dòng son dưỡng đình đám, được đánh giá cao nhất cao nhất trong cộng đồng làm đẹp", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(20, 7, "Power Youth Essence", 350000, R.drawable.kemskii, "50ml", " sử dụng sản phẩm tinh chất chống lão hóa RNA Power Youth Essence 50ml mỗi ngày để làn da luôn căng mịn tràn đầy khỏe khoắn và tươi trẻ", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(21, 8, "BÁNH TRUNG THU NGUYỆT ÁNH", 160000, R.drawable.banhtrungthu, "600g", "Trung thu là Tết đoàn viên, cho cả nhà quây quần bên ấm trà thơm, thưởng thức những vị bánh Trung thu ngọt lành từ Tôm hùm sốt Hồng Kông, Gà quay sốt X.O, Hạt sen Macca và Khoai môn hạt óc chó", "Hit", "Đức"));
                                sInstance.sanPhamDao().insertSanPham(new SanPhamEntry(22, 9, "BÁNH TRUNG THU BÍCH NGUYỆT", 160000, R.drawable.banhtrungthu2, "600g", "Hơn cả một món bánh truyền thống của dân tộc mùa lễ hội, những chiếc hộp mang tới vị bánh Trung thu: Ngũ nhân, Thập cẩm lạp xưởng, Hạt sen hạt chia, Sữa dừa chính là “bản giao hưởng đêm trăng”, là món ăn tinh thần cho cả gia đình trong đêm rằm tháng Tám", "Hit", "Đức"));
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
