package com.demo.component.commune;

import com.demo.component.commune.entity.Commune;
import com.demo.component.commune.entity.CommuneDto;
import com.demo.component.district.DistrictRepository;
import com.demo.component.district.entity.District;
import com.demo.component.province.ProvinceRepository;
import com.demo.component.province.entity.Province;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommuneService {
    @Autowired
    private CommuneRepository repository;

    @Autowired
    private DistrictRepository dtRepository;

    @Autowired
    private ProvinceRepository prRepository;

    private ModelMapper modelMapper = new ModelMapper();

    //    @Autowired


    public List<Commune> findAll() {
        return repository.findAll();
    }

    public CommuneDto save(CommuneDto commune) {
        Commune entity = modelMapper.map(commune, Commune.class);
        // check xem tỉnh thành có tồn tại hay không

        for (Province province : prRepository.findAll()
        ) {
            if (commune.getIdProvince().equals(province.getId())) {
                System.out.println("Tinh co ton tai");
            } else {
                System.out.println(commune.getIdProvince());
                System.out.println("khong ton tai");
//                System.out.println(prRepository.findAll()+ "hetttttttt");
            }
        }

        // check xem quận huyện có tồn tại hay không
//        System.out.println(commune.getIdDistrict());
        for (District district : dtRepository.findAll()
        ) {
            if (commune.getIdDistrict().equals(district.getId())) {
                System.out.println("Huyen co ton tai");
            } else {
                System.out.println(commune.getIdProvince());
                System.out.println("huyen khong ton tai");
            }
        }

        //set tỉnh thành và quận huyện
        entity.setDistrict(dtRepository.findById(UUID.fromString(commune.getIdDistrict())).get());
        entity.setProvince(prRepository.findById(UUID.fromString(commune.getIdProvince())).get());


        return null;
    }

    public Commune findById(UUID id) {
        return repository.findById(id).get();
    }

    public void delete(UUID id) {
        repository.delete(findById(id));
    }

//    public void update(String id, Commune commune) {
//        Commune newCom = repository.findById(id).get();
//        newCom.setName(commune.getName());
//        newCom.setCode(commune.getCode());
//        newCom.setFoundedYear(commune.getFoundedYear());
//        newCom.setAcreage(commune.getAcreage());
//        newCom.setNumberOfPeople(commune.getNumberOfPeople());
//        newCom.setDistrict(commune.getDistrict());
//        newCom.setProvince(commune.getProvince());
//        repository.save(newCom);
//    }

    public List<Commune> findByName(String name) {
        return repository.findByNameContains(name);
    }

    public List<Commune> findByCode(String code) {
        return repository.findByCodeContains(code);
    }

    public Page<Commune> pageCommune(int pageNumber, int totalItems) {
        Pageable pageable = PageRequest.of(
                pageNumber - 1, totalItems

        );
        return repository.findAll(pageable);
    }

    public void indexBooks() throws Exception {
        try {
            SessionFactory sessionFactory = null;
            Session session = sessionFactory.getCurrentSession();
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();
        } catch (Exception e) {
            throw e;
        }
    }

    public <QueryBuilder> List<Commune> search(String keyword) {
        SessionFactory sessionFactory = null;
        Session session = sessionFactory.getCurrentSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);

//        QueryBuilder qb = fullTextSession.getSearchFactory()
//                .buildQueryBuilder().forEntity(Commune.class).get();
//        Query query = qb
//                .keyword().onFields("id", "name", "code") // Chỉ định tìm theo cột nào
//                .matching(keyword)
//                .createQuery();
//
//        org.hibernate.Query hibQuery =
//                fullTextSession.createFullTextQuery(query, Commune.class);

//        List<Commune> results = hibQuery.list();
//        return results;
        return null;
    }


//    public void find(int )
//
//    public List<District> findByProvince(String idProvince) {
//        return repository.findByProvince(idProvince);
//    }


//    public void generateExcel(HttpServletResponse response) throws IOException {
//        List<Commune> listCommune = repository.findAll();
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("commune info");
//        HSSFRow row = sheet.createRow(0);
//        row.createCell(0).setCellValue("ID");
//        row.createCell(1).setCellValue("Code");
//        row.createCell(2).setCellValue("name");
//        row.createCell(3).setCellValue("founded year");
//        row.createCell(4).setCellValue("acreage");
//
//        int dataRowIndex = 1;
//
//        for (Commune commune : listCommune
//        ) {
//            HSSFRow dataRow = sheet.createRow(dataRowIndex);
//            dataRow.createCell(0).setCellValue(commune.getId());
//            dataRow.createCell(1).setCellValue(commune.getCode());
//            dataRow.createCell(2).setCellValue(commune.getName());
//            dataRow.createCell(3).setCellValue(commune.getFoundedYear());
//            dataRow.createCell(4).setCellValue(commune.getAcreage());
//            dataRowIndex++;
//        }
//        ServletOutputStream ops = response.getOutputStream();
//        workbook.write(ops);
//        workbook.close();
//        ops.close();
//
//    }
}
