package com.example.Billing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
class BillController {

    private final BillRepository billRepository;
    private final HistoryRepository historyRepository;

    BillController(BillRepository billRepository, HistoryRepository historyRepository) {
        this.billRepository = billRepository;
        this.historyRepository = historyRepository;
    }

    @GetMapping("/bills")
    public String getBills(Model model) {
        model.addAttribute("actualBills", billRepository.findAll());
        if (historyRepository.findAll().size() % 10 == 0) {
            model.addAttribute("aggregatedHistory", historyRepository.findAll());
        }
        return "bills";
    }

    @PostMapping("/updateAmount/{id}")
    public String updateAmount(@PathVariable Long id, @ModelAttribute Bill newBill, Model model) {
        Bill bill = billRepository.findById(id)
                .map(Bill -> {
                    Bill.setAccountNumber(newBill.getAccountNumber());
                    return billRepository.save(Bill);
                })
                .orElseThrow(() -> new BillNotFoundException(id.toString()));
        return getBills(model);
    }

    @GetMapping("/bills/{id}")
    String getBillById(@PathVariable Long id, Model model) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new BillNotFoundException(id.toString()));
        model.addAttribute("bill", bill);

        return "billDetails";
    }

    @GetMapping("/doTransaction")
    public String getTransactionUI(Model model) {
        model.addAttribute("transaction", new Transaction(0L, "HUF"));
        if (historyRepository.findAll().size() % 10 == 0) {
            model.addAttribute("aggregatedHistory", historyRepository.findAll());
        }
        return "transactionUI";
    }

    @PostMapping("/doTransaction")
    public String doTransaction(@ModelAttribute Transaction transaction, Model model) {
        Bill updatedBill = billRepository.findByAccountNumber(transaction.accountNumber)
                .map(bill -> {
                    bill.setAmount(bill.getAmount() + ((double) transaction.value)
                            * Currency.valueOf(transaction.getCurrency()).getValue() / Currency.valueOf(bill.getCurrency()).getValue());
                    return billRepository.save(bill);
                })
                .orElseThrow(() -> new BillNotFoundException(transaction.accountNumber));
        historyRepository.save(new History("The " + updatedBill.getAccountNumber() + "'s amount has changed with "
                + transaction.value + " " + transaction.currency + " to " + updatedBill.getAmount()));
        return getBills(model);
    }
}

