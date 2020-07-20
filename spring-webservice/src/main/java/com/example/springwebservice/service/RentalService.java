package com.example.springwebservice.service;

import com.example.springwebservice.domain.KakaoPay.Payment;
import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.cabinet.CabinetRepository;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.item.ItemRepository;
import com.example.springwebservice.domain.rent.Rent;
import com.example.springwebservice.domain.rent.RentRepository;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.domain.member.MemberRepository;
import com.example.springwebservice.service.mapper.ItemMapper;
import com.example.springwebservice.service.mapper.MemberMapper;
import com.example.springwebservice.service.mapper.RentMapper;
import com.example.springwebservice.web.RentSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class RentalService {

    @Autowired
    private ItemMapper itemMapper;
    private MemberMapper memberMapper;
    private RentMapper rentMapper;
    private RentRepository rentRepository;
    private CabinetRepository cabinetRepository;
    private ItemRepository itemRepository;
    private MemberRepository memberRepository;


    public ArrayList<Cabinet> matchCabinet(ArrayList<Integer> availableCabinetList){
        ArrayList<Cabinet> cabinets=new ArrayList<Cabinet>();
        List<Cabinet> cabinetList = cabinetRepository.findAll();
        for(Integer idx: availableCabinetList){
            for(Cabinet c: cabinetList){
                if(c.getCABINET_IDX()==idx){
                    cabinets.add(c);
                }
            }
        }
        System.out.println("cabinets size: "+cabinets.size());
        return cabinets;
    }


    public ArrayList<Cabinet> findCabinet(RentalRequestInfo info){
        List<Item> itemList = itemRepository.findAll();
        List<Cabinet> cabinetList = cabinetRepository.findAll();
        ArrayList<Integer> availableCabinetList= new ArrayList<Integer>();

        int cabinet[]=new int[cabinetList.size()+1];
        for (Item item:itemList){

            if(item.getCATEGORY_IDX()!=info.getCategory_idx()) continue;

            if(item.getSTART_TIME()==null){
                Integer cabinet_idx=item.getSTART_CABINET_IDX();
                cabinet[cabinet_idx]++;
                if(cabinet[cabinet_idx]==1){
                    System.out.println("cabinet_idx: "+cabinet_idx);
                    availableCabinetList.add(cabinet_idx);
                }
            }
            else if(item.getSTART_TIME().isBefore(info.getStart()) &&info.getEnd().isAfter(item.getEND_TIME())) continue;
            else if(info.getStart().isBefore(item.getEND_TIME())&&item.getEND_TIME().isBefore(info.getEnd())) continue;
            else if(info.getStart().isBefore(item.getSTART_TIME())&&item.getSTART_TIME().isBefore(info.getEnd())) continue;
            else{
                Integer cabinet_idx=item.getSTART_CABINET_IDX();
                cabinet[cabinet_idx]++;
                if(cabinet[cabinet_idx]==1){
                    availableCabinetList.add(cabinet_idx);
                }
            }
        }
        Integer minIdx=1, minVal=itemList.size();
        Integer maxIdx=1,maxVal=1;
        for (int i=1;i<cabinetList.size()+1;i++){
            if(cabinet[i]==0) continue;
            if(minVal>=cabinet[i]){
                minVal=cabinet[i];
                minIdx=i;
            }
            if(maxVal<=cabinet[i]){
                maxVal=cabinet[i];
                maxIdx=i;
            }
        }
        availableCabinetList.add(maxIdx);
        availableCabinetList.add(minIdx);
        System.out.println("availableCabinetList size: "+availableCabinetList.size());
        System.out.println("cabinet minIdx: "+minIdx);
        System.out.println("cabinet maxIdx: "+maxIdx);
        return matchCabinet(availableCabinetList);
    }
    public RentSaveRequestDto applyService(RentalRequestInfo info){
        RentSaveRequestDto dto=new RentSaveRequestDto();

        dto.setSTART_CABINET_IDX(info.getStart_cabinet_idx());
        dto.setEND_CABINET_IDX(info.getEnd_cabinet_idx());

        List<Item> itemList= itemMapper.findItemList(info.getCategory_idx());

        Integer item_idx=0;
        for(Item item: itemList){
            if(item.getSTATE()==1){
                item_idx=item.getITEM_IDX();
                itemMapper.updateUsedItem(item_idx);
                break;
            }
        }
        System.out.println("item_idx: "+item_idx);
        dto.setITEM_IDX(item_idx);
        dto.setUSER_ID(info.getUser_id());
        dto.setSTART_TIME(info.getStart());
        dto.setEND_TIME(info.getEnd());
        dto.setITEM_IDX(info.getItem_idx());
        dto.setAMOUNT(info.getTotal_amount());
        dto.setCATEGORY_IDX(info.getCategory_idx());
        dto.setAPPROVED_AT(LocalDateTime.now());
        dto.setSTART_CABINET_IDX(info.getStart_cabinet_idx());
        dto.setEND_CABINET_IDX(info.getEnd_cabinet_idx());
        dto.setSTATE(1);
        System.out.println("rent 정보 dto setting");

        return dto;
    }
    public List<Item> findItemList(Integer cabinetIdx){
        List<Item> itemList= itemMapper.findAll(cabinetIdx);

        return itemList;
    }

    public Item findAvailableItem(RentalRequestInfo info){
        System.out.print("Cabinet idx: "+info.getStart_cabinet_idx());
        List<Item> itemList=findItemList(info.getStart_cabinet_idx());
        System.out.print("itemList size: "+itemList.size());

        for (Item item:itemList){
            if(item.getSTART_TIME().isBefore(info.getStart()) &&info.getEnd().isAfter(item.getEND_TIME())) continue;
            else if(info.getStart().isBefore(item.getEND_TIME())&&item.getEND_TIME().isBefore(info.getEnd())) continue;
            else if(info.getStart().isBefore(item.getSTART_TIME())&&item.getSTART_TIME().isBefore(info.getEnd())) continue;
            else{
                return item;
            }
        }
        return null;
    }

    public List<Rent> returnRentList(String user_id){
        List<Rent> rent = rentMapper.findRentList(user_id);

        return rent;
    }

}
