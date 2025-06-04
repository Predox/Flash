from playwright.sync_api import sync_playwright, expect
import time

with sync_playwright() as p:
    browser = p.chromium.launch(headless=False)
    page = browser.new_page(
        base_url='http://localhost:8080/'
    )
    page.goto('index.html')
    page.fill('#email', 'pedro@example.com')
    page.fill('#senha', '1234')
    page.click('button[type="submit"]')
    try:
        expect(page).to_have_url('http://localhost:8080/home.html')
    except Exception:
        mensagem = page.locator('#mensagemLogin')
        try:
            if mensagem.text_content() == "Email ou senha inválidos.":
                print("Login Incorreto")
            else:
                print(f"Mensagem inesperada: {mensagem.text_content()}")
        except:
            print("Login falhou, mas sem mensagem clara de erro")



    
   