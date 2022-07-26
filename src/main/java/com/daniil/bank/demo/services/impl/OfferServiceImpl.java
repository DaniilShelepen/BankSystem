package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dal.repository.LegalOfferRepository;
import com.daniil.bank.demo.dal.repository.NaturalOfferRepository;
import com.daniil.bank.demo.dto.LegalOfferDto;
import com.daniil.bank.demo.dto.NaturalOfferDto;
import com.daniil.bank.demo.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final NaturalOfferRepository naturalOfferRepository;
    private final LegalOfferRepository legalOfferRepository;


    @Override
    public void createNaturalOffer(NaturalOfferDto naturalOfferDto) {

        naturalOfferRepository.save(NaturalOffer.builder()
                .description(naturalOfferDto.getDescription())
                .credit(naturalOfferDto.getCredit())
                .currency(naturalOfferDto.getCurrency())
                .client_status(naturalOfferDto.getClient_status())
                .percentageRate(naturalOfferDto.getPercentageRate())
                .available(naturalOfferDto.isAvailable())
                .build());

    }

    @Override
    public void createLegalOffer(LegalOfferDto legalOfferDto) {
        legalOfferRepository.save(LegalOffer.builder()
                .description(legalOfferDto.getDescription())
                .credit(legalOfferDto.getCredit())
                .currency(legalOfferDto.getCurrency())
                .client_status(legalOfferDto.getClient_status())
                .percentageRate(legalOfferDto.getPercentageRate())
                .available(legalOfferDto.isAvailable())
                .build());
    }


    @Override
    public void refactorNaturalOffer(Integer id, NaturalOfferDto naturalOfferDto) {
        NaturalOffer naturalOffer = naturalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo сюда свою ошибку впихни

        naturalOffer.setAvailable(naturalOfferDto.isAvailable());
        naturalOffer.setCredit(naturalOfferDto.getCredit());
        naturalOffer.setClient_status(naturalOfferDto.getClient_status());
        naturalOffer.setDescription(naturalOfferDto.getDescription());
        naturalOffer.setCurrency(naturalOfferDto.getCurrency());
        naturalOffer.setPercentageRate(naturalOfferDto.getPercentageRate());

        naturalOfferRepository.save(naturalOffer);

    }

    @Override
    public void refactorLegalOffer(Integer id, LegalOfferDto legalOfferDto) {

        LegalOffer legalOffer = legalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo сюда свою ошибку впихни

        legalOffer.setAvailable(legalOfferDto.isAvailable());
        legalOffer.setCredit(legalOfferDto.getCredit());
        legalOffer.setClient_status(legalOfferDto.getClient_status());
        legalOffer.setDescription(legalOfferDto.getDescription());
        legalOffer.setCurrency(legalOfferDto.getCurrency());
        legalOffer.setPercentageRate(legalOfferDto.getPercentageRate());

        legalOfferRepository.save(legalOffer);

    }

    @Override
    public void deleteNaturalOffer(Integer id) {
        naturalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo своя ошибка
        naturalOfferRepository.deleteById(id);

    }

    @Override
    public void deleteLegalOffer(Integer id) {
        legalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo своя ошибка
        legalOfferRepository.deleteById(id);
    }
}
